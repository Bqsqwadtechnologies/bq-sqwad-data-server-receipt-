package com.bqsqwad.datareceipt.ui.screens.receipts

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bqsqwad.datareceipt.ads.AdManager
import com.bqsqwad.datareceipt.ads.RewardedFeature
import com.bqsqwad.datareceipt.ads.RewardedGate
import com.bqsqwad.datareceipt.ads.UnlockStore
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import javax.inject.Inject

@HiltViewModel
class ReceiptPreviewViewModel @Inject constructor(
    val adManager: AdManager,
    val unlockStore: UnlockStore
) : ViewModel()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiptPreviewScreen(navController: NavController) {
    val viewModel: ReceiptPreviewViewModel = hiltViewModel()
    val premiumUnlocked by viewModel.unlockStore.isPremiumReceiptUnlocked.collectAsStateWithLifecycle(initialValue = false)
    var style by remember { mutableStateOf("Classic") }
    var showGate by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = context.findActivity()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Data Receipt") },
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Paper-style receipt card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFBF5))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("BQ SQWAD", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF0B2540))
                    Text("DATA RECEIPT", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF2AA9F5))
                    Text("─────────────────────", color = Color.Gray)
                    Spacer(Modifier.height(8.dp))
                    ReceiptLine("Period", "This week")
                    ReceiptLine("Total spent", "—")
                    ReceiptLine("Top app", "—")
                    ReceiptLine("Wi-Fi savings", "—")
                    Text("─────────────────────", color = Color.Gray)
                    Text("Made with BQ Sqwad Data Receipt", fontSize = 11.sp, color = Color.Gray)
                    Text("Style: $style", fontSize = 11.sp, color = Color.Gray)
                }
            }

            Text("Style", style = MaterialTheme.typography.titleSmall)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Classic", "Sky", "Night", "Minimal").forEach { s ->
                    val locked = s != "Classic" && !premiumUnlocked
                    FilterChip(
                        selected = style == s,
                        onClick = {
                            if (locked) showGate = true else style = s
                        },
                        label = { Text(if (locked) "$s 🔒" else s) }
                    )
                }
            }

            Button(
                onClick = {
                    val send = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "My Data Receipt — made with BQ Sqwad Data Receipt")
                    }
                    context.startActivity(Intent.createChooser(send, "Share receipt"))
                    activity?.let { viewModel.adManager.showInterstitialAfterShare(it) {}
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Share receipt")
            }
        }
    }

    RewardedGate(
        feature = RewardedFeature.PREMIUM_RECEIPT,
        visible = showGate,
        onDismiss = { showGate = false },
        onUnlocked = { showGate = false; style = "Sky" },
        adManager = viewModel.adManager,
        unlockStore = viewModel.unlockStore
    )
}

@Composable
private fun ReceiptLine(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color(0xFF5B7893), fontSize = 13.sp)
        Text(value, fontWeight = FontWeight.Medium, fontSize = 13.sp, color = Color(0xFF0B2540))
    }
    Spacer(Modifier.height(4.dp))
}

private fun android.content.Context.findActivity(): android.app.Activity? {
    var ctx = this
    while (ctx is android.content.ContextWrapper) {
        if (ctx is android.app.Activity) return ctx
        ctx = ctx.baseContext
    }
    return null
}
