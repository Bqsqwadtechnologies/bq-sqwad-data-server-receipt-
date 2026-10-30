package com.bqsqwad.datareceipt.ui.screens.receipts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReceiptsScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Receipts", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large
        ) {
            Box(Modifier.padding(32.dp), contentAlignment = Alignment.Center) {
                Text(
                    "Your weekly Data Receipts will appear here once usage data is available.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
