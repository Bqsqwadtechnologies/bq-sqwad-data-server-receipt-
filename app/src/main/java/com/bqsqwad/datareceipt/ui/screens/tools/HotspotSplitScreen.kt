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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Hotspot Split — calculator only.
 * Shows each person's share of hotspot cost. No payment features.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotspotSplitScreen(navController: NavController) {
    var totalCost by remember { mutableStateOf("") }
    var peopleCount by remember { mutableStateOf("2") }
    val clipboard = LocalClipboardManager.current

    val total = totalCost.toBigDecimalOrNull() ?: BigDecimal.ZERO
    val people = peopleCount.toIntOrNull()?.coerceAtLeast(1) ?: 1
    val share = if (people > 0) total.divide(BigDecimal(people), 2, RoundingMode.HALF_UP) else BigDecimal.ZERO

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hotspot Split") },
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
                "Split the cost of hotspot data among people. This is a calculator only — the app does not send money or payment requests.",
                style = MaterialTheme.typography.bodyMedium
            )

            OutlinedTextField(
                value = totalCost,
                onValueChange = { totalCost = it.filter { c -> c.isDigit() || c == '.' } },
                label = { Text("Total hotspot cost") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = peopleCount,
                onValueChange = { peopleCount = it.filter { c -> c.isDigit() } },
                label = { Text("Number of people") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Each person", style = MaterialTheme.typography.titleMedium)
                    Text(
                        share.toPlainString(),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            val message = "Hotspot split: ${total.toPlainString()} among $people people = ${share.toPlainString()} each."
            Button(
                onClick = { clipboard.setText(AnnotatedString(message)) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Copy share message")
            }
        }
    }
}

private fun String.toBigDecimalOrNull(): BigDecimal? = try {
    if (isBlank()) null else BigDecimal(this)
} catch (_: Exception) { null }
