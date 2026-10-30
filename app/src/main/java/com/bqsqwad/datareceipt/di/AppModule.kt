package com.bqsqwad.datareceipt.di

import android.content.Context
import androidx.room.Room
import com.bqsqwad.datareceipt.data.local.AppDatabase
import com.bqsqwad.datareceipt.domain.engine.CostEngine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "data_receipt.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideBundleDao(db: AppDatabase) = db.bundleDao()

    @Provides
    fun provideUsageSnapshotDao(db: AppDatabase) = db.usageSnapshotDao()

    @Provides
    @Singleton
    fun provideCostEngine(): CostEngine = CostEngine()
}
