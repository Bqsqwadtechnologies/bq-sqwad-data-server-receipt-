package com.bqsqwad.datareceipt.domain.engine

import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CostEngine @Inject constructor() {

    /**
     * cost = usedBytes / bundleBytes * bundlePrice
     * Uses BigDecimal only. Scale 2 for display money.
     */
    fun calculateCost(usedBytes: Long, bundleBytes: Long, bundlePrice: BigDecimal): BigDecimal {
        if (bundleBytes <= 0L || usedBytes <= 0L) return BigDecimal.ZERO.setScale(2)
        return BigDecimal.valueOf(usedBytes)
            .divide(BigDecimal.valueOf(bundleBytes), 8, RoundingMode.HALF_UP)
            .multiply(bundlePrice)
            .setScale(2, RoundingMode.HALF_UP)
    }

    fun remainingBytes(bundleBytes: Long, usedBytes: Long): Long =
        (bundleBytes - usedBytes).coerceAtLeast(0L)

    fun safeDailyBytes(remainingBytes: Long, remainingDays: Int): Long {
        if (remainingDays <= 0) return remainingBytes
        return remainingBytes / remainingDays
    }
}
