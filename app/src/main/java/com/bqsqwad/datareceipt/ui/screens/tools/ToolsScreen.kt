package com.bqsqwad.datareceipt.ui.screens.tools

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class ToolItem(val title: String, val icon: ImageVector, val description: String)

@Composable
fun ToolsScreen() {
    val tools = listOf(
        ToolItem("Live Cost Bubble", Icons.Default.BubbleChart, "Floating cost while you use data"),
        ToolItem("Cost Calculator", Icons.Default.Calculate, "Before you download"),
        ToolItem("Best Plan Finder", Icons.Default.Compare, "Compare carrier plans"),
        ToolItem("Budget Goal", Icons.Default.Savings, "Set a monthly money limit"),
        ToolItem("Calibrate", Icons.Default.Tune, "Match carrier balance"),
        ToolItem("Leak Detector", Icons.Default.Warning, "Apps using data while screen off"),
        ToolItem("Hotspot Cost", Icons.Default.WifiTethering, "Money used by sharing"),
        ToolItem("Travel Mode", Icons.Default.Flight, "Second currency for trips")
    )

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Tools", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tools) { tool ->
                Card(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().height(140.dp)
                ) {
                    Column(
                        Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(tool.icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Text(tool.title, style = MaterialTheme.typography.titleSmall)
                        Text(tool.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}
