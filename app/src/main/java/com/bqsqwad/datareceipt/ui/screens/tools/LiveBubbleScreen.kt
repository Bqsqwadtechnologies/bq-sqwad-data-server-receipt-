package com.bqsqwad.datareceipt.ui.screens.tools

import android.Manifest
import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bqsqwad.datareceipt.ads.AdManager
import com.bqsqwad.datareceipt.ads.RewardedFeature
import com.bqsqwad.datareceipt.ads.RewardedGate
import com.bqsqwad.datareceipt.ads.UnlockStore
import com.bqsqwad.datareceipt.service.LiveCostBubbleService
import dagger.hilt.android.lifecycle.HiltViewModel
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
    var showAttention by remember { mutableStateOf(true) }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { /* The feature remains usable if notifications are declined. */ }

    fun activateBubble() {
        val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val usageAllowed = runCatching {
            val mode = if (Build.VERSION.SDK_INT >= 29) {
                appOps.unsafeCheckOpNoThrow(
                    AppOpsManager.OPSTR_GET_USAGE_STATS,
                    android.os.Process.myUid(),
                    context.packageName
                )
            } else {
                @Suppress("DEPRECATION")
                appOps.checkOpNoThrow(
                    AppOpsManager.OPSTR_GET_USAGE_STATS,
                    android.os.Process.myUid(),
                    context.packageName
                )
            }
            mode == AppOpsManager.MODE_ALLOWED
        }.getOrDefault(false)
        if (!usageAllowed) {
            context.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
            return
        }
        if (!Settings.canDrawOverlays(context)) {
            context.startActivity(
                Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:${context.packageName}")
                )
            )
            return
        }
        ContextCompat.startForegroundService(
            context,
            Intent(context, LiveCostBubbleService::class.java)
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Live Data Monitor") },
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
            horizontalAlignment = Alignment.Start
        ) {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
                Row(
                    Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.onErrorContainer)
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("ATTENTION: MONITOR YOUR DATA", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onErrorContainer)
                        Text(
                            "Apps may transfer data in the background. Review your usage regularly so unexpected data consumption does not go unnoticed.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }
            }

            Text(
                "Track total data transferred during a live session and review the apps installed on this device. Usage details depend on Android's usage access and network-statistics availability.",
                style = MaterialTheme.typography.bodyLarge
            )

            if (isUnlocked) {
                AssistChip(onClick = {}, label = { Text("Live Bubble access active · 6 hours") })
                if (Build.VERSION.SDK_INT >= 33 &&
                    context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) !=
                    android.content.pm.PackageManager.PERMISSION_GRANTED
                ) {
                    OutlinedButton(
                        onClick = { notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS) },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Enable six-hour reminders") }
                }
                Button(onClick = { activateBubble() }, modifier = Modifier.fillMaxWidth()) {
                    Text("Start Live Bubble")
                }
            } else {
                Text("Your 6-hour access has ended.", style = MaterialTheme.typography.titleMedium)
                Button(onClick = { showGate = true }, modifier = Modifier.fillMaxWidth()) {
                    Text("Watch 2 ads · Activate for 6 hours")
                }
            }

            OutlinedButton(
                onClick = { navController.navigate(com.bqsqwad.datareceipt.ui.navigation.Routes.APPS) },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Review installed apps") }

            Text(
                "Permissions: Usage Access enables Android usage statistics. Display over other apps enables the floating bubble. Notifications enable six-hour reminders. Each permission can be declined in Android settings.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            OutlinedButton(
                onClick = {
                    context.startService(
                        Intent(context, LiveCostBubbleService::class.java)
                            .setAction(LiveCostBubbleService.ACTION_STOP)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Stop Live Bubble") }
        }
    }

    if (showAttention) {
        AlertDialog(
            onDismissRequest = { showAttention = false },
            icon = { Icon(Icons.Default.Warning, contentDescription = null) },
            title = { Text("Attention: Your data matters") },
            text = {
                Text("Background apps can use mobile data even when they are not open. Activate the Live Data Monitor to keep an eye on data transferred during your session and review your installed apps.")
            },
            confirmButton = {
                Button(onClick = {
                    showAttention = false
                    if (!isUnlocked) showGate = true
                }) { Text(if (isUnlocked) "Continue" else "Activate monitor") }
            },
            dismissButton = {
                TextButton(onClick = { showAttention = false }) { Text("Not now") }
            }
        )
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
