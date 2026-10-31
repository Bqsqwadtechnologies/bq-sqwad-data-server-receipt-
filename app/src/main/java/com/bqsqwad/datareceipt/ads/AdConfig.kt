package com.bqsqwad.datareceipt.ads

import com.bqsqwad.datareceipt.BuildConfig

/**
 * Central place for all AdMob unit IDs.
 * Debug builds always use Google official test IDs.
 * Release builds use the real production IDs from gradle.properties.
 */
object AdConfig {

    // Google official test IDs (safe for debug)
    private const val TEST_BANNER = "ca-app-pub-3940256099942544/6300978111"
    private const val TEST_REWARDED = "ca-app-pub-3940256099942544/5224354917"
    private const val TEST_INTERSTITIAL = "ca-app-pub-3940256099942544/1033173712"
    private const val TEST_APP_ID = "ca-app-pub-3940256099942544~3347511713"

    val appId: String
        get() = if (BuildConfig.DEBUG) TEST_APP_ID else BuildConfig.ADMOB_APP_ID

    val bannerHome: String
        get() = if (BuildConfig.DEBUG) TEST_BANNER else BuildConfig.ADMOB_BANNER_HOME

    val bannerApps: String
        get() = if (BuildConfig.DEBUG) TEST_BANNER else BuildConfig.ADMOB_BANNER_APPS

    val bannerTools: String
        get() = if (BuildConfig.DEBUG) TEST_BANNER else BuildConfig.ADMOB_BANNER_TOOLS

    val bannerReceipt: String
        get() = if (BuildConfig.DEBUG) TEST_BANNER else BuildConfig.ADMOB_BANNER_RECEIPT

    val bannerSettings: String
        get() = if (BuildConfig.DEBUG) TEST_BANNER else BuildConfig.ADMOB_BANNER_SETTINGS

    val bannerDetail: String
        get() = if (BuildConfig.DEBUG) TEST_BANNER else BuildConfig.ADMOB_BANNER_DETAIL

    val nativeApps: String
        get() = if (BuildConfig.DEBUG) TEST_BANNER else BuildConfig.ADMOB_NATIVE_APPS

    val nativeReceipts: String
        get() = if (BuildConfig.DEBUG) TEST_BANNER else BuildConfig.ADMOB_NATIVE_RECEIPTS

    val rewardedInsights: String
        get() = if (BuildConfig.DEBUG) TEST_REWARDED else BuildConfig.ADMOB_REWARDED_INSIGHTS

    val rewardedPass: String
        get() = if (BuildConfig.DEBUG) TEST_REWARDED else BuildConfig.ADMOB_REWARDED_PASS

    val rewardedWrapped: String
        get() = if (BuildConfig.DEBUG) TEST_REWARDED else BuildConfig.ADMOB_REWARDED_WRAPPED

    val rewardedLiveBubble: String
        get() = if (BuildConfig.DEBUG) TEST_REWARDED else BuildConfig.ADMOB_REWARDED_LIVE_BUBBLE

    val rewardedPremiumReceipt: String
        get() = if (BuildConfig.DEBUG) TEST_REWARDED else BuildConfig.ADMOB_REWARDED_PREMIUM_RECEIPT

    val rewardedExtendedHistory: String
        get() = if (BuildConfig.DEBUG) TEST_REWARDED else BuildConfig.ADMOB_REWARDED_EXTENDED_HISTORY

    val interstitialShare: String
        get() = if (BuildConfig.DEBUG) TEST_INTERSTITIAL else BuildConfig.ADMOB_INTERSTITIAL_SHARE
}
