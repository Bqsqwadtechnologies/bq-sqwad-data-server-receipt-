package com.bqsqwad.datareceipt.ui.screens.permissions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bqsqwad.datareceipt.util.PermissionHelper

/**
 * Prominent disclosure shown BEFORE sending the user to system Usage Access settings.
 * Required for Play policy compliance.
 */
@Composable
fun UsageAccessDisclosureScreen(
    onAgree: () -> Unit,
    onNotNow: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Usage Access permission",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Text(
            "To calculate how much each app costs, BQ Sqwad Data Receipt needs to read Android’s per-app network usage statistics.",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            "What we read",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            "• How much mobile and Wi-Fi data each app has used\n" +
            "• Whether the data was used while the app was in the foreground or background",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            "What we do not read",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            "• The content of your internet traffic\n" +
            "• Websites or apps you visit\n" +
            "• Messages, photos, or files\n" +
            "• Any data that leaves your device — everything stays on your phone",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            "We do not use a VPN and we never inspect the content of your traffic.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                onAgree()
                PermissionHelper.openUsageAccessSettings(context)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agree and continue")
        }
        TextButton(onClick = onNotNow) {
            Text("Not now")
        }
    }
}
