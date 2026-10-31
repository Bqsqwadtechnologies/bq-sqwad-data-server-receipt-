package com.bqsqwad.datareceipt.ui.screens.apps

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
fun AppsScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Apps", style = MaterialTheme.typography.headlineMedium)
        }
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Grant Usage Access to see which apps are costing you money. Per-app costs, background use, and cost-per-hour will appear here.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
        item {
            // Inline banner only after content, not next to search/sort (when those exist)
            InlineBannerSlot(placement = BannerPlacement.APPS)
        }
    }
}
