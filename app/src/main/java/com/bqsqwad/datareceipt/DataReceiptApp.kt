package com.bqsqwad.datareceipt

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.bqsqwad.datareceipt.ads.AdManager
import com.bqsqwad.datareceipt.ads.ConsentManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DataReceiptApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var adManager: AdManager

    @Inject
    lateinit var consentManager: ConsentManager

    override fun onCreate() {
        super.onCreate()
        // Ads are initialized only after consent is obtained (from MainActivity).
        // Do not call MobileAds.initialize here.
    }

    fun initializeAdsIfAllowed() {
        if (consentManager.canRequestAds) {
            adManager.initialize()
        }
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}
