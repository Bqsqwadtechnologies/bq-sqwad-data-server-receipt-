package com.bqsqwad.datareceipt.ui.screens.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bqsqwad.datareceipt.ui.MainViewModel
import com.bqsqwad.datareceipt.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemePickerScreen(navController: NavController) {
    val viewModel: MainViewModel = hiltViewModel()
    val currentTheme by viewModel.theme.collectAsStateWithLifecycle()

    val themes = listOf(
        AppTheme.SKY_LIGHT to "Sky Light",
        AppTheme.CLOUD_WHITE to "Cloud White",
        AppTheme.OCEAN_MIST to "Ocean Mist",
        AppTheme.SKY_NIGHT to "Sky Night",
        AppTheme.AMOLED_SKY to "AMOLED Sky",
        AppTheme.SUNRISE_SKY to "Sunrise Sky",
        AppTheme.FOLLOW_SYSTEM to "Follow system"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Theme") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(themes) { (theme, label) ->
                Card(
                    onClick = { viewModel.setTheme(theme) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(label, style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
                        if (currentTheme == theme) {
                            Icon(Icons.Default.Check, contentDescription = "Selected", tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}
