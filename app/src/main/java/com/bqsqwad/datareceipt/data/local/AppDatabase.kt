package com.bqsqwad.datareceipt.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bqsqwad.datareceipt.data.local.dao.BundleDao
import com.bqsqwad.datareceipt.data.local.dao.UsageSnapshotDao
import com.bqsqwad.datareceipt.data.local.entity.BundleEntity
import com.bqsqwad.datareceipt.data.local.entity.UsageSnapshotEntity

@Database(
    entities = [BundleEntity::class, UsageSnapshotEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bundleDao(): BundleDao
    abstract fun usageSnapshotDao(): UsageSnapshotDao
}
