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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ui.components.CompanyPlate
import com.bqsqwad.datareceipt.ui.navigation.Routes

@Composable
fun SettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(stringResource(R.string.nav_settings), style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        SettingsSection(stringResource(R.string.settings_profile)) {
            SettingsItem(Icons.Default.Public, "Country & Currency", "Nigeria · NGN") {
                navController.navigate(Routes.COUNTRY_PICKER)
            }
            SettingsItem(Icons.Default.Language, "Language", "English") {
                // Language picker placeholder – will open system or in-app
            }
        }

        SettingsSection(stringResource(R.string.settings_bundles)) {
            SettingsItem(Icons.Default.DataUsage, "Active Bundle", "None set") {
                navController.navigate(Routes.BUNDLE_EDITOR)
            }
            SettingsItem(Icons.Default.Add, "Add Bundle", null) {
                navController.navigate(Routes.BUNDLE_EDITOR)
            }
        }

        SettingsSection(stringResource(R.string.settings_appearance)) {
            SettingsItem(Icons.Default.Palette, "Theme", "Sky Light") {
                navController.navigate(Routes.THEME_PICKER)
            }
        }

        SettingsSection(stringResource(R.string.settings_privacy)) {
            SettingsItem(Icons.Default.PrivacyTip, stringResource(R.string.privacy_policy), null) {
                navController.navigate(Routes.PRIVACY)
            }
            SettingsItem(Icons.Default.Description, stringResource(R.string.terms_conditions), null) {
                navController.navigate(Routes.TERMS)
            }
            SettingsItem(Icons.Default.Copyright, stringResource(R.string.copyright_notices), null) {
                navController.navigate(Routes.COPYRIGHT)
            }
        }

        SettingsSection("More") {
            SettingsItem(Icons.Default.Apps, stringResource(R.string.more_from_company), null) {
                navController.navigate(Routes.MORE_FROM)
            }
            SettingsItem(Icons.Default.Info, stringResource(R.string.settings_about), "v1.0.0") {
                navController.navigate(Routes.ABOUT)
            }
            SettingsItem(Icons.Default.Help, "Help & FAQ", null) {
                navController.navigate(Routes.HELP)
            }
        }

        Spacer(Modifier.height(24.dp))
        CompanyPlate(
            onPrivacy = { navController.navigate(Routes.PRIVACY) },
            onTerms = { navController.navigate(Routes.TERMS) },
            onCopyright = { navController.navigate(Routes.COPYRIGHT) },
            onMore = { navController.navigate(Routes.MORE_FROM) }
        )
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
private fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String?,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(role = Role.Button, onClick = onClick)
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
