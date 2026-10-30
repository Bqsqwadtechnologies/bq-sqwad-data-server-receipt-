package com.bqsqwad.datareceipt.ui.screens.apps

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bqsqwad.datareceipt.ads.BannerAdView
import com.bqsqwad.datareceipt.ads.BannerPlacement

@Composable
fun AppsScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text("Apps", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))
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
                        "Grant Usage Access permission to see which apps are costing you money.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // Banner Ad (Apps)
        BannerAdView(
            placement = BannerPlacement.APPS,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}
