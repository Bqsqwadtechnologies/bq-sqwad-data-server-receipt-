package com.bqsqwad.datareceipt.ui.screens.wrapped

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataWrappedScreen(navController: NavController) {
    val pages = listOf(
        "Your month in data" to "A quick look at how you used mobile data.",
        "Total spend" to "Your estimated mobile data spend will appear here.",
        "Biggest app" to "The app that cost you the most this month.",
        "Biggest day" to "The day you used the most data.",
        "Wi-Fi savings" to "How much you avoided spending by using Wi-Fi.",
        "Background waste" to "Data used while the screen was off.",
        "Best week" to "Your most efficient week.",
        "See you next month" to "Made with BQ Sqwad Data Receipt."
    )
    val pagerState = rememberPagerState(pageCount = { pages.size })

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Data Wrapped") },
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(pages[page].first, style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)
                        Spacer(Modifier.height(16.dp))
                        Text(pages[page].second, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
                    }
                }
            }
            Text("${pagerState.currentPage + 1} / ${pages.size}", style = MaterialTheme.typography.labelMedium)
            Text("Premium styles can be unlocked with a short ad when data is available.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
