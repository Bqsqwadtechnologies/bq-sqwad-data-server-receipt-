package com.bqsqwad.datareceipt.ui.screens.apps

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bqsqwad.datareceipt.ads.BannerPlacement
import com.bqsqwad.datareceipt.ads.InlineBannerSlot

private data class InstalledApp(val name: String, val packageName: String, val isSystem: Boolean)

@Composable
fun AppsScreen() {
    val context = LocalContext.current
    var apps by remember { mutableStateOf<List<InstalledApp>>(emptyList()) }
    var loadError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(context) {
        try {
            val pm = context.packageManager
            apps = pm.getInstalledApplications(0)
                .map { info ->
                    InstalledApp(
                        name = pm.getApplicationLabel(info).toString(),
                        packageName = info.packageName,
                        isSystem = (info.flags and ApplicationInfo.FLAG_SYSTEM) != 0
                    )
                }
                .sortedBy { it.name.lowercase() }
        } catch (error: Exception) {
            loadError = error.localizedMessage ?: "Unable to load installed apps"
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text("Apps on this device", style = MaterialTheme.typography.headlineMedium)
                Text(
                    "Showing installed apps visible to BQ Sqwad Data Receipt. Usage access is separately required for per-app usage statistics.",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(8.dp))
            }
            if (loadError != null) {
                item { Text(loadError!!, color = MaterialTheme.colorScheme.error) }
            } else if (apps.isEmpty()) {
                item {
                    Box(Modifier.fillMaxWidth().padding(24.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            } else {
                item { Text("${apps.size} installed apps", style = MaterialTheme.typography.titleSmall) }
                items(apps, key = { it.packageName }) { app ->
                    ListItem(
                        headlineContent = { Text(app.name) },
                        supportingContent = {
                            Text(if (app.isSystem) "System app" else "Installed app")
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
        InlineBannerSlot(placement = BannerPlacement.APPS)
    }
}