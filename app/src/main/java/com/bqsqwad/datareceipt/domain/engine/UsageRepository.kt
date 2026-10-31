package com.bqsqwad.datareceipt.domain.engine

import android.app.AppOpsManager
import android.app.usage.NetworkStats
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.net.ConnectivityManager
import android.os.Process
import dagger.hilt.android.qualifiers.ApplicationContext
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

data class AppUsage(
    val packageName: String,
    val uid: Int,
    val mobileBytes: Long,
    val wifiBytes: Long,
    val foregroundBytes: Long,
    val backgroundBytes: Long
) {
    val totalBytes: Long get() = mobileBytes + wifiBytes
}

/**
 * Reads Android's device network-statistics buckets locally.
 * Results are estimates supplied by Android and may be unavailable on some devices/carriers.
 */
@Singleton
class UsageRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val costEngine: CostEngine
) {
    fun hasUsagePermission(): Boolean {
        return try {
            val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val mode = if (android.os.Build.VERSION.SDK_INT >= 29) {
                appOps.unsafeCheckOpNoThrow(
                    AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(), context.packageName
                )
            } else {
                @Suppress("DEPRECATION")
                appOps.checkOpNoThrow(
                    AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(), context.packageName
                )
            }
            mode == AppOpsManager.MODE_ALLOWED
        } catch (_: Exception) {
            false
        }
    }

    fun getUsageForPeriod(start: Long, end: Long): List<AppUsage> {
        if (!hasUsagePermission() || end <= start) return emptyList()
        val manager = context.getSystemService(Context.NETWORK_STATS_SERVICE) as? NetworkStatsManager
            ?: return emptyList()
        data class Totals(
            var mobile: Long = 0L,
            var wifi: Long = 0L,
            var foreground: Long = 0L,
            var background: Long = 0L
        )
        val totals = mutableMapOf<Int, Totals>()

        fun collect(networkType: Int, mobile: Boolean) {
            var stats: NetworkStats? = null
            try {
                stats = manager.querySummary(networkType, null, start, end)
                val bucket = NetworkStats.Bucket()
                while (stats.hasNextBucket()) {
                    if (!stats.getNextBucket(bucket)) break
                    val uid = bucket.uid
                    if (uid < 0 || bucket.tag != NetworkStats.Bucket.TAG_NONE) continue
                    val bytes = (bucket.rxBytes + bucket.txBytes).coerceAtLeast(0L)
                    val item = totals.getOrPut(uid) { Totals() }
                    if (mobile) item.mobile += bytes else item.wifi += bytes
                    if (bucket.state == NetworkStats.Bucket.STATE_FOREGROUND) {
                        item.foreground += bytes
                    } else {
                        item.background += bytes
                    }
                }
            } catch (_: SecurityException) {
                // Usage Access is not granted or this Android build restricts network history.
            } catch (_: RuntimeException) {
                // Some manufacturers do not expose all network templates.
            } finally {
                runCatching { stats?.close() }
            }
        }

        collect(ConnectivityManager.TYPE_MOBILE, mobile = true)
        collect(ConnectivityManager.TYPE_WIFI, mobile = false)

        return totals.map { (uid, value) ->
            AppUsage(
                packageName = "",
                uid = uid,
                mobileBytes = value.mobile,
                wifiBytes = value.wifi,
                foregroundBytes = value.foreground,
                backgroundBytes = value.background
            )
        }.sortedByDescending { it.totalBytes }
    }

    fun calculateCost(usedBytes: Long, bundleBytes: Long, price: BigDecimal): BigDecimal {
        return costEngine.calculateCost(usedBytes, bundleBytes, price)
    }
}
