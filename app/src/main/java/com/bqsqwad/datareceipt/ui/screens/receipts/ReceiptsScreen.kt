package com.bqsqwad.datareceipt.ui.screens.receipts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bqsqwad.datareceipt.ads.BannerPlacement
import com.bqsqwad.datareceipt.ads.InlineBannerSlot

@Composable
fun ReceiptsScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("Receipts", style = MaterialTheme.typography.headlineMedium)
        }
        item {
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
                        "Premium styles and extended history can be unlocked with a short ad.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
        item {
            InlineBannerSlot(placement = BannerPlacement.RECEIPT)
        }
    }
}
