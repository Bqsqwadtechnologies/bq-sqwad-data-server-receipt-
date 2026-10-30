package com.bqsqwad.datareceipt.domain.model

import java.math.BigDecimal
import java.time.Instant

data class DataBundle(
    val id: String,
    val name: String,
    val price: BigDecimal,
    val sizeBytes: Long,
    val validityDays: Int,
    val startAt: Instant,
    val isActive: Boolean = true,
    val currencyCode: String = "NGN"
) {
    val endAt: Instant
        get() = startAt.plusSeconds(validityDays.toLong() * 24 * 3600)
}
