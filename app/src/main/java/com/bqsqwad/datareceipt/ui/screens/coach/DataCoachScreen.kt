package com.bqsqwad.datareceipt.ui.screens.coach

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataCoachScreen(navController: NavController) {
    val context = LocalContext.current

    val tips = listOf(
        "Turn off auto-play videos in social apps" to null,
        "Update apps over Wi-Fi only" to Settings.ACTION_WIRELESS_SETTINGS,
        "Review apps using background data" to Settings.ACTION_USAGE_ACCESS_SETTINGS,
        "Enable Data Saver when on mobile data" to "android.settings.DATA_SAVER_SETTINGS",
        "Restrict background data for heavy apps" to Settings.ACTION_APPLICATION_SETTINGS
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Data Coach") },
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "Simple, on-device tips based on common data habits. Full personalized plan can be unlocked with a short ad when usage data is available.",
                style = MaterialTheme.typography.bodyMedium
            )
            tips.forEach { (tip, action) ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(tip, style = MaterialTheme.typography.titleSmall)
                        if (action != null) {
                            TextButton(onClick = {
                                try {
                                    context.startActivity(Intent(action))
                                } catch (_: Exception) { }
                            }) {
                                Text("Open settings")
                            }
                        }
                    }
                }
            }
        }
    }
}
