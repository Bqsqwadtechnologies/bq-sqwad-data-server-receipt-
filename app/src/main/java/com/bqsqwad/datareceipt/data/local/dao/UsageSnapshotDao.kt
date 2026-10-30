package com.bqsqwad.datareceipt.data.local.dao

import androidx.room.*
import com.bqsqwad.datareceipt.data.local.entity.UsageSnapshotEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsageSnapshotDao {
    @Query("SELECT * FROM usage_snapshots WHERE dateEpochDay = :day")
    fun observeForDay(day: Long): Flow<List<UsageSnapshotEntity>>

    @Query("SELECT * FROM usage_snapshots WHERE dateEpochDay BETWEEN :start AND :end")
    fun observeRange(start: Long, end: Long): Flow<List<UsageSnapshotEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<UsageSnapshotEntity>)

    @Query("DELETE FROM usage_snapshots WHERE dateEpochDay < :before")
    suspend fun pruneBefore(before: Long)
}
