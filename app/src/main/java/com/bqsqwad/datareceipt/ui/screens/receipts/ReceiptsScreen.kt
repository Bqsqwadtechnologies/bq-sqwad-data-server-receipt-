package com.bqsqwad.datareceipt.ui.screens.receipts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bqsqwad.datareceipt.ads.BannerAdView
import com.bqsqwad.datareceipt.ads.BannerPlacement

@Composable
fun ReceiptsScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Receipts", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Box(
                    Modifier.padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Your weekly Data Receipts will appear here once usage data is available.\n\n" +
                        "Watch a short ad to unlock premium receipt styles and extended history.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // Banner Ad (Receipt)
        BannerAdView(
            placement = BannerPlacement.RECEIPT,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}
