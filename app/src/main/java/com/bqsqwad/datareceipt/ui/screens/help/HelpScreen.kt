package com.bqsqwad.datareceipt.ui.screens.help

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Help & FAQ") },
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
            Text("How it works", style = MaterialTheme.typography.titleLarge)
            Text(
                "BQ Sqwad Data Receipt reads Android’s network usage statistics (with your permission) and the data bundle details you enter. It estimates how much each app is costing you and when your bundle may run out.",
                style = MaterialTheme.typography.bodyMedium
            )

            Text("Why do estimates differ from my carrier?", style = MaterialTheme.typography.titleMedium)
            Text(
                "Carriers may round differently, exclude some traffic, or update balances on a delay. Use Calibrate to align the app with the balance your carrier shows.",
                style = MaterialTheme.typography.bodyMedium
            )

            Text("Does the app use a VPN?", style = MaterialTheme.typography.titleMedium)
            Text(
                "No. The app never creates a VPN and never inspects the content of your traffic. It only reads the usage totals Android already keeps.",
                style = MaterialTheme.typography.bodyMedium
            )

            Text("Is this a payment or banking app?", style = MaterialTheme.typography.titleMedium)
            Text(
                "No. Data Budget and Hotspot Split are calculators only. The app does not move money, buy data, or connect to any payment system.",
                style = MaterialTheme.typography.bodyMedium
            )

            Text("Contact", style = MaterialTheme.typography.titleMedium)
            Text("hello@bqsqwadai.com", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
