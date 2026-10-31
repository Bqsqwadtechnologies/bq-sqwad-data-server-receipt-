package com.bqsqwad.datareceipt.ui.screens.tools

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalibrateScreen(navController: NavController) {
    var carrierBalance by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calibrate") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Enter the data balance reported by your carrier. The app will store an offset so future estimates stay closer to the carrier’s numbers. Message formats vary — always check the result.",
                style = MaterialTheme.typography.bodyMedium
            )

            OutlinedTextField(
                value = carrierBalance,
                onValueChange = { carrierBalance = it },
                label = { Text("Carrier reported balance (e.g. 1.2 GB or 1200 MB)") },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                "You can also paste a carrier balance message (user-initiated paste only — the app does not read SMS).",
                style = MaterialTheme.typography.bodySmall
            )

            Button(
                onClick = { /* store offset */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save calibration")
            }
        }
    }
}
