package com.bqsqwad.datareceipt.ads

import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

enum class BannerPlacement { HOME, APPS, TOOLS, RECEIPT }

@Composable
fun BannerAdView(
    placement: BannerPlacement,
    modifier: Modifier = Modifier
) {
    val unitId = when (placement) {
        BannerPlacement.HOME -> AdConfig.bannerHome
        BannerPlacement.APPS -> AdConfig.bannerApps
        BannerPlacement.TOOLS -> AdConfig.bannerTools
        BannerPlacement.RECEIPT -> AdConfig.bannerReceipt
    }

    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = unitId
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}
