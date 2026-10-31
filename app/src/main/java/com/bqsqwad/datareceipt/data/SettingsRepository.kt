package com.bqsqwad.datareceipt.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bqsqwad.datareceipt.ui.theme.AppTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.settingsDataStore by preferencesDataStore("settings")

@Singleton
class SettingsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val onboardingDoneKey = booleanPreferencesKey("onboarding_done")
    private val themeKey = stringPreferencesKey("theme")
    private val materialYouKey = booleanPreferencesKey("material_you")
    private val countryCodeKey = stringPreferencesKey("country_code")
    private val currencyCodeKey = stringPreferencesKey("currency_code")
    private val use1024Key = booleanPreferencesKey("use_1024")
    private val demoModeKey = booleanPreferencesKey("demo_mode")

    val isOnboardingDone: Flow<Boolean> = context.settingsDataStore.data.map { it[onboardingDoneKey] ?: false }
    val theme: Flow<AppTheme> = context.settingsDataStore.data.map {
        try { AppTheme.valueOf(it[themeKey] ?: AppTheme.SKY_LIGHT.name) } catch (_: Exception) { AppTheme.SKY_LIGHT }
    }
    val useMaterialYou: Flow<Boolean> = context.settingsDataStore.data.map { it[materialYouKey] ?: false }
    val countryCode: Flow<String> = context.settingsDataStore.data.map { it[countryCodeKey] ?: "NG" }
    val currencyCode: Flow<String> = context.settingsDataStore.data.map { it[currencyCodeKey] ?: "NGN" }
    val use1024: Flow<Boolean> = context.settingsDataStore.data.map { it[use1024Key] ?: true }
    val isDemoMode: Flow<Boolean> = context.settingsDataStore.data.map { it[demoModeKey] ?: false }

    suspend fun setOnboardingDone(done: Boolean) {
        context.settingsDataStore.edit { it[onboardingDoneKey] = done }
    }

    suspend fun setTheme(theme: AppTheme) {
        context.settingsDataStore.edit { it[themeKey] = theme.name }
    }

    suspend fun setMaterialYou(enabled: Boolean) {
        context.settingsDataStore.edit { it[materialYouKey] = enabled }
    }

    suspend fun setCountryAndCurrency(country: String, currency: String) {
        context.settingsDataStore.edit {
            it[countryCodeKey] = country
            it[currencyCodeKey] = currency
        }
    }

    suspend fun setUse1024(use: Boolean) {
        context.settingsDataStore.edit { it[use1024Key] = use }
    }

    suspend fun setDemoMode(enabled: Boolean) {
        context.settingsDataStore.edit { it[demoModeKey] = enabled }
    }
}
