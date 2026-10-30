package com.bqsqwad.datareceipt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bundles")
data class BundleEntity(
    @PrimaryKey val id: String,
    val name: String,
    val price: String, // BigDecimal as string
    val sizeBytes: Long,
    val validityDays: Int,
    val startAtEpoch: Long,
    val isActive: Boolean,
    val currencyCode: String
)
