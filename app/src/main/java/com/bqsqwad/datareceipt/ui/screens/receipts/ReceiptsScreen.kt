package com.bqsqwad.datareceipt.ui.screens.receipts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bqsqwad.datareceipt.ads.BannerPlacement
import com.bqsqwad.datareceipt.ads.InlineBannerSlot
import com.bqsqwad.datareceipt.ui.navigation.Routes

@Composable
fun ReceiptsScreen(navController: NavController? = null) {
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
                onClick = { navController?.navigate(Routes.RECEIPT_PREVIEW) },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Box(Modifier.padding(32.dp), contentAlignment = Alignment.Center) {
                    Text(
                        "Tap to open a sample Data Receipt.\n\nPremium styles unlock with a short ad. Share triggers an interstitial when available.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
        item {
            OutlinedButton(
                onClick = { navController?.navigate(Routes.DATA_WRAPPED) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Data Wrapped (monthly recap)")
            }
        }
        item {
            OutlinedButton(
                onClick = { navController?.navigate(Routes.DATA_COACH) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Data Coach")
            }
        }
        item {
            InlineBannerSlot(placement = BannerPlacement.RECEIPT)
        }
    }
}
