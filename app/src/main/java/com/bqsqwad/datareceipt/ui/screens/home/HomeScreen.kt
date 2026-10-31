package com.bqsqwad.datareceipt.ui.screens.home

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ads.BannerAdView
import com.bqsqwad.datareceipt.ads.BannerPlacement
import com.bqsqwad.datareceipt.ui.components.CompanyPlate
import com.bqsqwad.datareceipt.ui.theme.SkyPrimary
import com.bqsqwad.datareceipt.ui.theme.SkyTint

@Composable
fun HomeScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = SkyTint)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(SkyPrimary.copy(alpha = 0.25f), SkyTint)
                        )
                    )
                    .padding(24.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.spent_this_period),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "₦0.00",
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 42.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = stringResource(R.string.of_bundle, "₦0"),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Grant access card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(role = Role.Button) {
                    context.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
                },
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(Modifier.padding(20.dp)) {
                Text(stringResource(R.string.usage_access_title), style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                Text(
                    stringResource(R.string.usage_access_message),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(12.dp))
                Button(onClick = {
                    context.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
                }) {
                    Text(stringResource(R.string.action_grant_access))
                }
            }
        }

        // Runway card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(Modifier.padding(20.dp)) {
                Text("Bundle Runway", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                Text(
                    "Set up a data bundle to see predictions",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Top spenders
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(Modifier.padding(20.dp)) {
                Text(stringResource(R.string.top_spenders), style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(12.dp))
                Text(
                    "Grant Usage Access to see per-app costs",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Tip of the day
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = SkyTint)
        ) {
            Column(Modifier.padding(20.dp)) {
                Text(stringResource(R.string.tip_of_the_day), style = MaterialTheme.typography.titleSmall)
                Spacer(Modifier.height(8.dp))
                Text(
                    "Turn off auto-play videos on social apps to save significant data.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        CompanyPlate()

        BannerAdView(placement = BannerPlacement.HOME)

        Spacer(Modifier.height(24.dp))
    }
}
