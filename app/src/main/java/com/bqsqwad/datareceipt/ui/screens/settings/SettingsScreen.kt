package com.bqsqwad.datareceipt.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ui.components.CompanyPlate

@Composable
fun SettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        SettingsSection("Profile & Region") {
            SettingsItem(Icons.Default.Public, "Country & Currency", "Nigeria · NGN")
            SettingsItem(Icons.Default.Language, "Language", "English")
        }

        SettingsSection("My Bundles") {
            SettingsItem(Icons.Default.DataUsage, "Active Bundle", "None set")
            SettingsItem(Icons.Default.Add, "Add Bundle", null)
        }

        SettingsSection("Appearance") {
            SettingsItem(Icons.Default.Palette, "Theme", "Sky Light")
        }

        SettingsSection("Privacy & Ads") {
            SettingsItem(Icons.Default.PrivacyTip, stringResource(R.string.privacy_policy), null)
            SettingsItem(Icons.Default.Description, stringResource(R.string.terms_conditions), null)
            SettingsItem(Icons.Default.Copyright, stringResource(R.string.copyright_notices), null)
        }

        SettingsSection("More") {
            SettingsItem(Icons.Default.Apps, stringResource(R.string.more_from_bq_sqwad), null)
            SettingsItem(Icons.Default.Info, "About", "v1.0.0")
        }

        Spacer(Modifier.height(24.dp))
        CompanyPlate()
        Spacer(Modifier.height(32.dp))
    }
}

@Composable
private fun SettingsSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Text(
        title,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 12.dp)
    )
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Column { content() }
    }
    Spacer(Modifier.height(8.dp))
}

@Composable
private fun SettingsItem(icon: ImageVector, title: String, subtitle: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyLarge)
            if (subtitle != null) {
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
