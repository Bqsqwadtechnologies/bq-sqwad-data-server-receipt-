package com.bqsqwad.datareceipt.ads

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bqsqwad.datareceipt.R
import kotlinx.coroutines.launch

enum class RewardedFeature {
    LIVE_BUBBLE,
    PREMIUM_RECEIPT,
    EXTENDED_HISTORY,
    WEEKLY_INSIGHTS,
    ALL_ACCESS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardedGate(
    feature: RewardedFeature,
    visible: Boolean,
    onDismiss: () -> Unit,
    onUnlocked: () -> Unit,
    adManager: AdManager,
    unlockStore: UnlockStore
) {
    if (!visible) return

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var state by remember { mutableStateOf<GateState>(GateState.Ready) }

    val title = when (feature) {
        RewardedFeature.LIVE_BUBBLE -> "Unlock Live Cost Bubble"
        RewardedFeature.PREMIUM_RECEIPT -> "Unlock Premium Receipt Styles"
        RewardedFeature.EXTENDED_HISTORY -> "Unlock Extended History"
        RewardedFeature.WEEKLY_INSIGHTS -> "Unlock Weekly Insights"
        RewardedFeature.ALL_ACCESS -> "Unlock All-Access Pass"
    }

    val benefit = if (feature == RewardedFeature.LIVE_BUBBLE) {
        "Watch two short ads to activate this feature for 6 hours."
    } else {
        "Watch a short ad to unlock this feature for 24 hours."
    }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Text(benefit, style = MaterialTheme.typography.bodyMedium)

            when (state) {
                GateState.Ready -> {
                    Button(
                        onClick = {
                            state = GateState.Loading
                            val activity = context.findActivity()
                            if (activity == null) {
                                state = GateState.Failed
                                return@Button
                            }
                            when (feature) {
                                RewardedFeature.LIVE_BUBBLE -> adManager.showTwoRewardedLiveBubble(
                                    activity,
                                    onRewarded = {
                                        scope.launch {
                                            unlockStore.unlockLiveBubble()
                                            state = GateState.Success
                                            onUnlocked()
                                            onDismiss()
                                        }
                                    },
                                    onFailed = { state = GateState.Failed }
                                )
                                RewardedFeature.PREMIUM_RECEIPT -> adManager.showRewardedPremiumReceipt(
                                    activity,
                                    onRewarded = {
                                        scope.launch {
                                            unlockStore.unlockPremiumReceipt()
                                            state = GateState.Success
                                            onUnlocked()
                                            onDismiss()
                                        }
                                    },
                                    onFailed = { state = GateState.Failed }
                                )
                                RewardedFeature.EXTENDED_HISTORY -> adManager.showRewardedExtendedHistory(
                                    activity,
                                    onRewarded = {
                                        scope.launch {
                                            unlockStore.unlockExtendedHistory()
                                            state = GateState.Success
                                            onUnlocked()
                                            onDismiss()
                                        }
                                    },
                                    onFailed = { state = GateState.Failed }
                                )
                                else -> {
                                    // Insights / All-Access use same rewarded unit for now until new IDs added
                                    adManager.showRewardedLiveBubble(
                                        activity,
                                        onRewarded = {
                                            scope.launch {
                                                unlockStore.unlockLiveBubble()
                                                state = GateState.Success
                                                onUnlocked()
                                                onDismiss()
                                            }
                                        },
                                        onFailed = { state = GateState.Failed }
                                    )
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            if (feature == RewardedFeature.LIVE_BUBBLE) {
                                "Watch 2 short ads"
                            } else {
                                stringResource(R.string.watch_ad)
                            }
                        )
                    }
                    TextButton(onClick = onDismiss) {
                        Text(stringResource(R.string.not_now))
                    }
                }
                GateState.Loading -> {
                    CircularProgressIndicator()
                    Text(stringResource(R.string.preparing_ad))
                }
                GateState.Success -> {
                    Text(stringResource(R.string.unlocked_for))
                }
                GateState.Failed -> {
                    Text(stringResource(R.string.no_ad_available))
                    Button(onClick = { state = GateState.Ready }) {
                        Text(stringResource(R.string.action_retry))
                    }
                    TextButton(onClick = onDismiss) {
                        Text(stringResource(R.string.not_now))
                    }
                }
            }
        }
    }
}

private sealed class GateState {
    data object Ready : GateState()
    data object Loading : GateState()
    data object Success : GateState()
    data object Failed : GateState()
}

private fun android.content.Context.findActivity(): Activity? {
    var ctx = this
    while (ctx is android.content.ContextWrapper) {
        if (ctx is Activity) return ctx
        ctx = ctx.baseContext
    }
    return null
}
