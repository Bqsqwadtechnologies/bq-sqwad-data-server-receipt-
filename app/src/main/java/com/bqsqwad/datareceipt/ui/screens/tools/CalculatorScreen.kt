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
import java.math.BigDecimal
import java.math.RoundingMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(navController: NavController) {
    var fileSizeMb by remember { mutableStateOf("") }
    var videoMinutes by remember { mutableStateOf("") }
    var videoQuality by remember { mutableStateOf("720p") }
    var musicMinutes by remember { mutableStateOf("") }

    // Approximate data rates (MB per minute)
    val videoRates = mapOf(
        "144p" to 1.5,
        "360p" to 4.0,
        "480p" to 7.0,
        "720p" to 15.0,
        "1080p" to 35.0
    )
    val musicMbPerMin = 1.2

    val fileMb = fileSizeMb.toDoubleOrNull() ?: 0.0
    val videoMb = (videoMinutes.toDoubleOrNull() ?: 0.0) * (videoRates[videoQuality] ?: 15.0)
    val musicMb = (musicMinutes.toDoubleOrNull() ?: 0.0) * musicMbPerMin
    val totalMb = fileMb + videoMb + musicMb

    // Example cost assuming a 1 GB = ₦500 bundle (user will later use real bundle)
    val examplePricePerGb = BigDecimal("500")
    val cost = BigDecimal.valueOf(totalMb)
        .divide(BigDecimal("1024"), 6, RoundingMode.HALF_UP)
        .multiply(examplePricePerGb)
        .setScale(2, RoundingMode.HALF_UP)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cost Calculator") },
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
            Text("Estimate data and money before you download or stream.", style = MaterialTheme.typography.bodyMedium)

            OutlinedTextField(
                value = fileSizeMb,
                onValueChange = { fileSizeMb = it.filter { c -> c.isDigit() || c == '.' } },
                label = { Text("File size (MB)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = videoMinutes,
                onValueChange = { videoMinutes = it.filter { c -> c.isDigit() } },
                label = { Text("Video minutes") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Text("Video quality", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("144p", "360p", "480p", "720p", "1080p").forEach { q ->
                    FilterChip(
                        selected = videoQuality == q,
                        onClick = { videoQuality = q },
                        label = { Text(q) }
                    )
                }
            }

            OutlinedTextField(
                value = musicMinutes,
                onValueChange = { musicMinutes = it.filter { c -> c.isDigit() } },
                label = { Text("Music / audio minutes") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Estimated data", style = MaterialTheme.typography.titleMedium)
                    Text("%.1f MB".format(totalMb), style = MaterialTheme.typography.headlineSmall)
                    Text("Example cost (₦500 / GB)", style = MaterialTheme.typography.titleSmall)
                    Text("₦$cost", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
                    Text("Uses your real bundle price once a bundle is set.", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
