package com.bqsqwad.datareceipt.ads

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.unlockDataStore by preferencesDataStore("unlocks")

@Singleton
class UnlockStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val liveBubbleKey = longPreferencesKey("live_bubble_until")
    private val premiumReceiptKey = longPreferencesKey("premium_receipt_until")
    private val extendedHistoryKey = longPreferencesKey("extended_history_until")

    private val TWENTY_FOUR_HOURS = 24 * 60 * 60 * 1000L

    val isLiveBubbleUnlocked: Flow<Boolean> = context.unlockDataStore.data.map { prefs ->
        (prefs[liveBubbleKey] ?: 0L) > System.currentTimeMillis()
    }

    val isPremiumReceiptUnlocked: Flow<Boolean> = context.unlockDataStore.data.map { prefs ->
        (prefs[premiumReceiptKey] ?: 0L) > System.currentTimeMillis()
    }

    val isExtendedHistoryUnlocked: Flow<Boolean> = context.unlockDataStore.data.map { prefs ->
        (prefs[extendedHistoryKey] ?: 0L) > System.currentTimeMillis()
    }

    suspend fun unlockLiveBubble() {
        context.unlockDataStore.edit { it[liveBubbleKey] = System.currentTimeMillis() + TWENTY_FOUR_HOURS }
    }

    suspend fun unlockPremiumReceipt() {
        context.unlockDataStore.edit { it[premiumReceiptKey] = System.currentTimeMillis() + TWENTY_FOUR_HOURS }
    }

    suspend fun unlockExtendedHistory() {
        context.unlockDataStore.edit { it[extendedHistoryKey] = System.currentTimeMillis() + TWENTY_FOUR_HOURS }
    }
}
