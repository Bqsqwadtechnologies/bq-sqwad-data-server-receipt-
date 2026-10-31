package com.bqsqwad.datareceipt.ui.screens.more

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ui.components.CompanyPlate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreFromScreen(navController: NavController) {
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.more_from_company)) },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("BQ Sqwad Technologies", style = MaterialTheme.typography.headlineSmall)
            Text("More apps and entertainment from us.", style = MaterialTheme.typography.bodyMedium)

            // Recommended apps
            Text(stringResource(R.string.recommended), style = MaterialTheme.typography.titleMedium)

            RecommendedAppCard(
                name = "BQ Sqwad AI",
                tagline = "Your AI companion",
                onClick = { /* Play Store when URL available */ }
            )
            RecommendedAppCard(
                name = "BQ Sqwad Data Saver",
                tagline = "Save more data every day",
                onClick = { /* Play Store when URL available */ }
            )

            // Entertainment
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("BQ Sqwad Entertainment", style = MaterialTheme.typography.titleMedium)
                    Text("Movies, shows and original content", style = MaterialTheme.typography.bodySmall)
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(onClick = { uriHandler.openUri("https://youtube.com/@bqsqwad") }) {
                            Text(stringResource(R.string.watch_on_youtube))
                        }
                        OutlinedButton(onClick = { uriHandler.openUri("https://www.tiktok.com/@bqsqwad") }) {
                            Text(stringResource(R.string.follow_on_tiktok))
                        }
                    }
                }
            }

            CompanyPlate(
                onPrivacy = { navController.navigate("privacy") },
                onTerms = { navController.navigate("terms") },
                onCopyright = { navController.navigate("copyright") }
            )
        }
    }
}

@Composable
private fun RecommendedAppCard(name: String, tagline: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(name, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.width(8.dp))
                    AssistChip(onClick = {}, label = { Text(stringResource(R.string.recommended)) })
                }
                Text(tagline, style = MaterialTheme.typography.bodySmall)
            }
            Text(stringResource(R.string.coming_soon), style = MaterialTheme.typography.labelMedium)
        }
    }
}
