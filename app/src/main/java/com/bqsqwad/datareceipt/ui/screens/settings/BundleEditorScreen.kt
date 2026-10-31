package com.bqsqwad.datareceipt.ui.screens.settings

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
fun BundleEditorScreen(navController: NavController) {
    var price by remember { mutableStateOf("") }
    var size by remember { mutableStateOf("") }
    var unitGb by remember { mutableStateOf(true) }
    var days by remember { mutableStateOf("30") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Data Bundle") },
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
                "Enter the data bundle you bought. The app uses this to estimate costs and runway. Prices are what you paid — the app never buys data for you.",
                style = MaterialTheme.typography.bodyMedium
            )

            OutlinedTextField(
                value = price,
                onValueChange = { price = it.filter { c -> c.isDigit() || c == '.' } },
                label = { Text("Price paid") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = size,
                onValueChange = { size = it.filter { c -> c.isDigit() || c == '.' } },
                label = { Text("Size") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(selected = unitGb, onClick = { unitGb = true }, label = { Text("GB") })
                FilterChip(selected = !unitGb, onClick = { unitGb = false }, label = { Text("MB") })
            }

            OutlinedTextField(
                value = days,
                onValueChange = { days = it.filter { c -> c.isDigit() } },
                label = { Text("Validity (days)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Text("1 GB = 1024 MB (default). You can change this in advanced settings later.", style = MaterialTheme.typography.bodySmall)

            Button(
                onClick = { /* persist bundle via repository next */ navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save bundle")
            }
        }
    }
}
