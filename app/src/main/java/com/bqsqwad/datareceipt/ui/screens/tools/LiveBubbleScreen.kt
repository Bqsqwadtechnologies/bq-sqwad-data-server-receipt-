package com.bqsqwad.datareceipt.ui.screens.tools

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bqsqwad.datareceipt.ads.AdManager
import com.bqsqwad.datareceipt.ads.RewardedFeature
import com.bqsqwad.datareceipt.ads.RewardedGate
import com.bqsqwad.datareceipt.ads.UnlockStore
import com.bqsqwad.datareceipt.service.LiveCostBubbleService
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import javax.inject.Inject

@HiltViewModel
class LiveBubbleViewModel @Inject constructor(
    val adManager: AdManager,
    val unlockStore: UnlockStore
) : ViewModel()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveBubbleScreen(navController: NavController) {
    val viewModel: LiveBubbleViewModel = hiltViewModel()
    val context = LocalContext.current
    val isUnlocked by viewModel.unlockStore.isLiveBubbleUnlocked.collectAsStateWithLifecycle(initialValue = false)
    var showGate by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Live Cost Bubble") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Show total data transferred on this device during the current live session.",
                style = MaterialTheme.typography.bodyLarge
            )
            if (isUnlocked) {
                AssistChip(onClick = {}, label = { Text("Unlocked · active for 24 hours") })
                Button(
                    onClick = {
                        if (!Settings.canDrawOverlays(context)) {
                            context.startActivity(
                                Intent(
                                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                    Uri.parse("package:${context.packageName}")
                                )
                            )
                        } else {
                            ContextCompat.startForegroundService(
                                context,
                                Intent(context, LiveCostBubbleService::class.java)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Start Live Bubble") }
                OutlinedButton(
                    onClick = {
                        context.startService(
                            Intent(context, LiveCostBubbleService::class.java)
                                .setAction(LiveCostBubbleService.ACTION_STOP)
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Stop Live Bubble") }
                Text(
                    "Android will ask for permission to display the bubble over other apps. The live counter shows total device data transferred, not per-app charges.",
                    style = MaterialTheme.typography.bodySmall
                )
            } else {
                Text("This feature is locked.", style = MaterialTheme.typography.titleMedium)
                Button(onClick = { showGate = true }, modifier = Modifier.fillMaxWidth()) {
                    Text("Unlock for 24 hours")
                }
            }
        }
    }

    RewardedGate(
        feature = RewardedFeature.LIVE_BUBBLE,
        visible = showGate,
        onDismiss = { showGate = false },
        onUnlocked = { showGate = false },
        adManager = viewModel.adManager,
        unlockStore = viewModel.unlockStore
    )
}