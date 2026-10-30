package com.bqsqwad.datareceipt.data.local.dao

import androidx.room.*
import com.bqsqwad.datareceipt.data.local.entity.BundleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BundleDao {
    @Query("SELECT * FROM bundles ORDER BY startAtEpoch DESC")
    fun observeAll(): Flow<List<BundleEntity>>

    @Query("SELECT * FROM bundles WHERE isActive = 1 LIMIT 1")
    fun observeActive(): Flow<BundleEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bundle: BundleEntity)

    @Update
    suspend fun update(bundle: BundleEntity)

    @Query("UPDATE bundles SET isActive = 0")
    suspend fun deactivateAll()

    @Query("DELETE FROM bundles WHERE id = :id")
    suspend fun delete(id: String)
}
