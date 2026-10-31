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

/**
 * Data Budget — set a monthly spending limit for mobile data.
 * Not a financial product. No money is moved. Alerts only.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataBudgetScreen(navController: NavController) {
    var budgetAmount by remember { mutableStateOf("") }
    var alert50 by remember { mutableStateOf(true) }
    var alert80 by remember { mutableStateOf(true) }
    var alert100 by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Data Budget") },
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
                "Set a monthly limit for how much you want to spend on mobile data. The app will notify you at the thresholds you choose. This is not a bank account or payment feature.",
                style = MaterialTheme.typography.bodyMedium
            )

            OutlinedTextField(
                value = budgetAmount,
                onValueChange = { budgetAmount = it.filter { c -> c.isDigit() || c == '.' } },
                label = { Text("Monthly data budget") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            Text("Alert me at", style = MaterialTheme.typography.titleSmall)
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = alert50, onCheckedChange = { alert50 = it })
                Text("50%")
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = alert80, onCheckedChange = { alert80 = it })
                Text("80%")
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = alert100, onCheckedChange = { alert100 = it })
                Text("100%")
            }

            Button(
                onClick = { /* persist via SettingsRepository in next iteration */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Data Budget")
            }
        }
    }
}
