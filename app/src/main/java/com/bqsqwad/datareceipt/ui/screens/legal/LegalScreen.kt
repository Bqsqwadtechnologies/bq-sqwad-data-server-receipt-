package com.bqsqwad.datareceipt.ui.screens.legal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ui.components.CompanyPlate

enum class LegalType { PRIVACY, TERMS, COPYRIGHT }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LegalScreen(type: LegalType, navController: NavController) {
    val title = when (type) {
        LegalType.PRIVACY -> stringResource(R.string.privacy_policy)
        LegalType.TERMS -> stringResource(R.string.terms_conditions)
        LegalType.COPYRIGHT -> stringResource(R.string.copyright_notices)
    }
    val uriHandler = LocalUriHandler.current
    val onlineUrl = when (type) {
        LegalType.PRIVACY -> "https://bqsqwadtechnologies.github.io/bq-sqwad-data-server-receipt-/privacy.html"
        LegalType.TERMS -> "https://bqsqwadtechnologies.github.io/bq-sqwad-data-server-receipt-/terms.html"
        LegalType.COPYRIGHT -> "https://bqsqwadtechnologies.github.io/bq-sqwad-data-server-receipt-/copyright.html"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = { uriHandler.openUri(onlineUrl) }) {
                        Text(stringResource(R.string.view_online))
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
                .padding(16.dp)
        ) {
            Text(
                text = when (type) {
                    LegalType.PRIVACY -> "We are BQ Sqwad Technologies. This Privacy Policy explains how BQ Sqwad Data Receipt works.\n\nThe app processes network usage statistics only on your device. No account is required and no usage data is uploaded to our servers.\n\nPermissions: Usage Access (to read Android NetworkStats), Notifications, optional Display over other apps, Internet (for ads).\n\nWe do not use a VPN and never inspect the content of your traffic.\n\nAdvertising is provided by Google AdMob. You can manage consent under Privacy & ads choices.\n\nContact: hello@bqsqwadai.com"
                    LegalType.TERMS -> "By using BQ Sqwad Data Receipt you agree to these Terms.\n\nThe costs shown are estimates based on Android usage statistics and the bundle information you enter. They may differ from your carrier’s billing. A calibration tool is provided.\n\nThe free version shows ads. An optional Remove Ads purchase may be offered.\n\nGoverning law: Federal Republic of Nigeria.\n\nContact: hello@bqsqwadai.com"
                    LegalType.COPYRIGHT -> "© 2026 BQ Sqwad Technologies. All rights reserved.\n\nBQ Sqwad, BQ Sqwad Data Receipt, BQ Sqwad AI, BQ Sqwad Data Saver and associated logos are trademarks of BQ Sqwad Technologies.\n\nThird-party trademarks belong to their respective owners."
                },
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(32.dp))
            CompanyPlate(
                onPrivacy = { if (type != LegalType.PRIVACY) navController.navigate("privacy") },
                onTerms = { if (type != LegalType.TERMS) navController.navigate("terms") },
                onCopyright = { if (type != LegalType.COPYRIGHT) navController.navigate("copyright") }
            )
        }
    }
}
