package com.bqsqwad.datareceipt.domain.engine

import android.app.usage.NetworkStatsManager
import android.content.Context
import android.net.ConnectivityManager
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
)

/**
 * Reads Android NetworkStatsManager.
 * Full implementation will query buckets per UID.
 * Currently provides safe empty results so the app never crashes.
 */
@Singleton
class UsageRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val costEngine: CostEngine
) {
    fun hasUsagePermission(): Boolean {
        return try {
            val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as android.app.AppOpsManager
            val mode = appOps.unsafeCheckOpNoThrow(
                "android:get_usage_stats",
                android.os.Process.myUid(),
                context.packageName
            )
            mode == android.app.AppOpsManager.MODE_ALLOWED
        } catch (_: Exception) {
            false
        }
    }

    fun getUsageForPeriod(start: Long, end: Long): List<AppUsage> {
        // Full NetworkStatsManager query will be expanded.
        // Returning empty list is safe and prevents crashes.
        return emptyList()
    }

    fun calculateCost(usedBytes: Long, bundleBytes: Long, price: BigDecimal): BigDecimal {
        return costEngine.calculateCost(usedBytes, bundleBytes, price)
    }
}
