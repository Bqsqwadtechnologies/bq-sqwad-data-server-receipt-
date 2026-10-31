package com.bqsqwad.datareceipt.ads

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

/**
 * Inline adaptive banner inside scrolling content.
 * Never place near bottom navigation or interactive controls.
 */
@Composable
fun InlineBannerSlot(
    placement: BannerPlacement,
    modifier: Modifier = Modifier
) {
    val unitId = when (placement) {
        BannerPlacement.HOME -> AdConfig.bannerHome
        BannerPlacement.APPS -> AdConfig.bannerApps
        BannerPlacement.TOOLS -> AdConfig.bannerTools
        BannerPlacement.RECEIPT -> AdConfig.bannerReceipt
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ad",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(4.dp))

        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            factory = { ctx ->
                AdView(ctx).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = unitId
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}
