package com.bqsqwad.datareceipt.domain.engine

import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject
import javax.inject.Singleton

/**
 * All money calculations use BigDecimal. Never floating point.
 */
@Singleton
class CostEngine @Inject constructor() {

    fun calculateCost(
        usedBytes: Long,
        bundleBytes: Long,
        bundlePrice: BigDecimal
    ): BigDecimal {
        if (bundleBytes <= 0L || usedBytes <= 0L) return BigDecimal.ZERO
        val ratio = BigDecimal.valueOf(usedBytes)
            .divide(BigDecimal.valueOf(bundleBytes), 12, RoundingMode.HALF_UP)
        return ratio.multiply(bundlePrice).setScale(4, RoundingMode.HALF_UP)
    }

    fun bytesFromGb(gb: Double, use1024: Boolean = true): Long {
        val factor = if (use1024) 1024.0 else 1000.0
        return (gb * factor * factor * factor).toLong()
    }

    fun formatBytes(bytes: Long, use1024: Boolean = true): String {
        val unit = if (use1024) 1024.0 else 1000.0
        return when {
            bytes < unit -> "$bytes B"
            bytes < unit * unit -> String.format("%.1f KB", bytes / unit)
            bytes < unit * unit * unit -> String.format("%.1f MB", bytes / (unit * unit))
            else -> String.format("%.2f GB", bytes / (unit * unit * unit))
        }
    }
}
