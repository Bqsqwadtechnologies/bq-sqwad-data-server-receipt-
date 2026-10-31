package com.bqsqwad.datareceipt.ui.screens.apps

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bqsqwad.datareceipt.ads.BannerPlacement
import com.bqsqwad.datareceipt.ads.InlineBannerSlot
import com.bqsqwad.datareceipt.domain.engine.UsageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private data class InstalledApp(
    val name: String,
    val packageName: String,
    val isSystem: Boolean,
    val bytes: Long
)

@HiltViewModel
class AppsViewModel @Inject constructor(
    private val usageRepository: UsageRepository
) : ViewModel() {
    var apps by mutableStateOf<List<InstalledApp>>(emptyList())
        private set
    var hasUsageAccess by mutableStateOf(false)
        private set
    var loading by mutableStateOf(false)
        private set
    var loadError by mutableStateOf<String?>(null)
        private set

    fun load(context: Context) {
        viewModelScope.launch {
            loading = true
            loadError = null
            try {
                hasUsageAccess = usageRepository.hasUsagePermission()
                val usage = if (hasUsageAccess) {
                    withContext(Dispatchers.IO) {
                        usageRepository.getUsageForPeriod(
                            System.currentTimeMillis() - 24L * 60L * 60L * 1000L,
                            System.currentTimeMillis()
                        ).associate { it.uid to it.totalBytes }
                    }
                } else emptyMap()
                val installed = withContext(Dispatchers.IO) {
                    val pm = context.packageManager
                    pm.getInstalledApplications(0).map { info ->
                        InstalledApp(
                            name = pm.getApplicationLabel(info).toString(),
                            packageName = info.packageName,
                            isSystem = (info.flags and ApplicationInfo.FLAG_SYSTEM) != 0,
                            bytes = usage[info.uid] ?: 0L
                        )
                    }.sortedWith(compareByDescending<InstalledApp> { it.bytes }.thenBy { it.name.lowercase() })
                }
                apps = installed
            } catch (error: Exception) {
                loadError = error.localizedMessage ?: "Unable to load installed apps or usage statistics."
            } finally {
                loading = false
            }
        }
    }
}

@Composable
fun AppsScreen() {
    val context = LocalContext.current
    val viewModel: AppsViewModel = hiltViewModel()

    LaunchedEffect(context) { viewModel.load(context) }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text("Apps & data usage", style = MaterialTheme.typography.headlineMedium)
                Text(
                    "Android network totals for the last 24 hours. Figures are estimates and may not be available for every app or network.",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(8.dp))
            }
            if (!viewModel.hasUsageAccess) {
                item {
                    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Enable Usage Access", style = MaterialTheme.typography.titleMedium)
                            Text("Allow Android usage access to display available per-app network totals.")
                            Button(onClick = {
                                context.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
                            }) { Text("Grant Usage Access") }
                        }
                    }
                }
            }
            if (viewModel.loadError != null) {
                item { Text(viewModel.loadError!!, color = MaterialTheme.colorScheme.error) }
            } else if (viewModel.loading) {
                item {
                    Box(Modifier.fillMaxWidth().padding(24.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            } else if (viewModel.apps.isEmpty()) {
                item { Text("No installed-app information is available on this device.") }
            } else {
                item { Text("${viewModel.apps.size} installed apps", style = MaterialTheme.typography.titleSmall) }
                items(viewModel.apps, key = { it.packageName }) { app ->
                    ListItem(
                        headlineContent = { Text(app.name) },
                        supportingContent = {
                            Text(
                                (if (app.isSystem) "System app" else "Installed app") +
                                    if (viewModel.hasUsageAccess) " · ${"%.2f".format(app.bytes / (1024.0 * 1024.0))} MB in 24h" else ""
                            )
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
        InlineBannerSlot(placement = BannerPlacement.APPS)
    }
}
