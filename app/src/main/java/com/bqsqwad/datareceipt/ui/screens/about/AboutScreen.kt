package com.bqsqwad.datareceipt.ui.screens.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ui.components.CompanyPlate
import com.bqsqwad.datareceipt.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings_about)) },
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Text(
                stringResource(R.string.tagline),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Text("Version information available in app settings")
            Spacer(Modifier.height(16.dp))
            TextButton(onClick = { navController.navigate(Routes.PRIVACY) }) {
                Text(stringResource(R.string.privacy_policy))
            }
            TextButton(onClick = { navController.navigate(Routes.TERMS) }) {
                Text(stringResource(R.string.terms_conditions))
            }
            TextButton(onClick = { navController.navigate(Routes.COPYRIGHT) }) {
                Text(stringResource(R.string.copyright_notices))
            }
            TextButton(onClick = { navController.navigate(Routes.MORE_FROM) }) {
                Text(stringResource(R.string.more_from_company))
            }
            Spacer(Modifier.height(24.dp))
            CompanyPlate(
                onPrivacy = { navController.navigate(Routes.PRIVACY) },
                onTerms = { navController.navigate(Routes.TERMS) },
                onCopyright = { navController.navigate(Routes.COPYRIGHT) },
                onMore = { navController.navigate(Routes.MORE_FROM) }
            )
        }
    }
}
