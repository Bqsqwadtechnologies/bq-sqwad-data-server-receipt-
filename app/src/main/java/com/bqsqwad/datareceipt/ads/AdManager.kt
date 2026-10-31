package com.bqsqwad.datareceipt.ads

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var interstitialShare: InterstitialAd? = null
    private var rewardedLiveBubble: RewardedAd? = null
    private var rewardedPremiumReceipt: RewardedAd? = null
    private var rewardedExtendedHistory: RewardedAd? = null

    private var lastInterstitialTime = 0L
    private val interstitialCooldownMs = 3 * 60 * 1000L // 3 minutes

    fun initialize(onComplete: () -> Unit = {}) {
        MobileAds.initialize(context) {
            Log.d("AdManager", "AdMob initialized")
            preloadAll()
            onComplete()
        }
    }

    private fun preloadAll() {
        loadInterstitialShare()
        loadRewardedLiveBubble()
        loadRewardedPremiumReceipt()
        loadRewardedExtendedHistory()
    }

    // ---------- Interstitial (after share) ----------
    private fun loadInterstitialShare() {
        InterstitialAd.load(
            context,
            AdConfig.interstitialShare,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialShare = ad
                }
                override fun onAdFailedToLoad(error: LoadAdError) {
                    interstitialShare = null
                    Log.w("AdManager", "Interstitial failed: ${error.message}")
                }
            }
        )
    }

    fun showInterstitialAfterShare(activity: Activity, onDismissed: () -> Unit = {}) {
        val now = System.currentTimeMillis()
        if (now - lastInterstitialTime < interstitialCooldownMs) {
            onDismissed()
            return
        }
        val ad = interstitialShare
        if (ad != null) {
            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    interstitialShare = null
                    lastInterstitialTime = System.currentTimeMillis()
                    loadInterstitialShare()
                    onDismissed()
                }
                override fun onAdFailedToShowFullScreenContent(error: AdError) {
                    interstitialShare = null
                    loadInterstitialShare()
                    onDismissed()
                }
            }
            ad.show(activity)
        } else {
            loadInterstitialShare()
            onDismissed()
        }
    }

    // ---------- Rewarded: Live Bubble (24h) ----------
    private fun loadRewardedLiveBubble() {
        RewardedAd.load(
            context,
            AdConfig.rewardedLiveBubble,
            AdRequest.Builder().build(),
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) { rewardedLiveBubble = ad }
                override fun onAdFailedToLoad(error: LoadAdError) { rewardedLiveBubble = null }
            }
        )
    }

    fun showRewardedLiveBubble(activity: Activity, onRewarded: () -> Unit, onFailed: () -> Unit = {}) {
        val ad = rewardedLiveBubble
        if (ad != null) {
            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    rewardedLiveBubble = null
                    loadRewardedLiveBubble()
                }
            }
            ad.show(activity) { onRewarded() }
        } else {
            loadRewardedLiveBubble()
            onFailed()
        }
    }

    /**
     * Requires two completed rewarded ads before granting the Live Bubble unlock.
     * The second ad is loaded after the first closes so it is not skipped when
     * the preloaded ad is consumed.
     */
    fun showTwoRewardedLiveBubble(
        activity: Activity,
        onRewarded: () -> Unit,
        onFailed: () -> Unit = {}
    ) {
        val firstAd = rewardedLiveBubble
        if (firstAd == null) {
            loadRewardedLiveBubble()
            onFailed()
            return
        }
        rewardedLiveBubble = null
        var firstRewardEarned = false
        firstAd.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                loadRewardedLiveBubble()
                onFailed()
            }

            override fun onAdDismissedFullScreenContent() {
                if (!firstRewardEarned) {
                    loadRewardedLiveBubble()
                    onFailed()
                    return
                }
                RewardedAd.load(
                    context,
                    AdConfig.rewardedLiveBubble,
                    AdRequest.Builder().build(),
                    object : RewardedAdLoadCallback() {
                        override fun onAdLoaded(secondAd: RewardedAd) {
                            var secondRewardEarned = false
                            secondAd.fullScreenContentCallback = object : FullScreenContentCallback() {
                                override fun onAdFailedToShowFullScreenContent(error: AdError) {
                                    loadRewardedLiveBubble()
                                    onFailed()
                                }

                                override fun onAdDismissedFullScreenContent() {
                                    loadRewardedLiveBubble()
                                    if (secondRewardEarned) onRewarded() else onFailed()
                                }
                            }
                            secondAd.show(activity) { secondRewardEarned = true }
                        }

                        override fun onAdFailedToLoad(error: LoadAdError) {
                            loadRewardedLiveBubble()
                            onFailed()
                        }
                    }
                )
            }
        }
        firstAd.show(activity) { firstRewardEarned = true }
    }

    // ---------- Rewarded: Premium Receipt (24h) ----------
    private fun loadRewardedPremiumReceipt() {
        RewardedAd.load(
            context,
            AdConfig.rewardedPremiumReceipt,
            AdRequest.Builder().build(),
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) { rewardedPremiumReceipt = ad }
                override fun onAdFailedToLoad(error: LoadAdError) { rewardedPremiumReceipt = null }
            }
        )
    }

    fun showRewardedPremiumReceipt(activity: Activity, onRewarded: () -> Unit, onFailed: () -> Unit = {}) {
        val ad = rewardedPremiumReceipt
        if (ad != null) {
            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    rewardedPremiumReceipt = null
                    loadRewardedPremiumReceipt()
                }
            }
            ad.show(activity) { onRewarded() }
        } else {
            loadRewardedPremiumReceipt()
            onFailed()
        }
    }

    // ---------- Rewarded: Extended History (24h) ----------
    private fun loadRewardedExtendedHistory() {
        RewardedAd.load(
            context,
            AdConfig.rewardedExtendedHistory,
            AdRequest.Builder().build(),
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) { rewardedExtendedHistory = ad }
                override fun onAdFailedToLoad(error: LoadAdError) { rewardedExtendedHistory = null }
            }
        )
    }

    fun showRewardedExtendedHistory(activity: Activity, onRewarded: () -> Unit, onFailed: () -> Unit = {}) {
        val ad = rewardedExtendedHistory
        if (ad != null) {
            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    rewardedExtendedHistory = null
                    loadRewardedExtendedHistory()
                }
            }
            ad.show(activity) { onRewarded() }
        } else {
            loadRewardedExtendedHistory()
            onFailed()
        }
    }
}
