package com.bqsqwad.datareceipt.ui.screens.tools

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ads.BannerPlacement
import com.bqsqwad.datareceipt.ads.InlineBannerSlot
import com.bqsqwad.datareceipt.ui.navigation.Routes

data class ToolItem(
    val titleRes: Int,
    val icon: ImageVector,
    val description: String,
    val route: String,
    val isPremium: Boolean = false
)

@Composable
fun ToolsScreen(navController: NavController? = null) {
    val tools = listOf(
        ToolItem(R.string.tool_live_bubble, Icons.Default.BubbleChart, "Floating cost while you use data", Routes.LIVE_BUBBLE, isPremium = true),
        ToolItem(R.string.tool_calculator, Icons.Default.Calculate, "Before you download", Routes.CALCULATOR),
        ToolItem(R.string.tool_plan_finder, Icons.Default.Compare, "Compare carrier plans", Routes.PLAN_FINDER),
        ToolItem(R.string.tool_budget, Icons.Default.Savings, "Set a monthly data budget", Routes.BUDGET),
        ToolItem(R.string.tool_calibrate, Icons.Default.Tune, "Match carrier balance", Routes.CALIBRATE),
        ToolItem(R.string.tool_leak, Icons.Default.Warning, "Apps using data while screen off", Routes.LEAK_DETECTOR),
        ToolItem(R.string.tool_hotspot, Icons.Default.WifiTethering, "Split hotspot cost (calculator)", Routes.HOTSPOT),
        ToolItem(R.string.tool_travel, Icons.Default.Flight, "Second currency for trips", Routes.TRAVEL)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text(stringResource(R.string.nav_tools), style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tools) { tool ->
                    Card(
                        onClick = { navController?.navigate(tool.route) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                    ) {
                        Column(
                            Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                Icon(tool.icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                if (tool.isPremium) {
                                    Text("🔒", style = MaterialTheme.typography.labelSmall)
                                }
                            }
                            Text(stringResource(tool.titleRes), style = MaterialTheme.typography.titleSmall)
                            Text(
                                tool.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Inline banner below the grid (not between cards, not near nav)
        InlineBannerSlot(placement = BannerPlacement.TOOLS)
    }
}
