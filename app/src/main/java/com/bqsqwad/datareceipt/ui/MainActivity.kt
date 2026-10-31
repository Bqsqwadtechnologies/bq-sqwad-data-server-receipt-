package com.bqsqwad.datareceipt.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bqsqwad.datareceipt.DataReceiptApp
import com.bqsqwad.datareceipt.ads.ConsentManager
import com.bqsqwad.datareceipt.ui.navigation.AppNavHost
import com.bqsqwad.datareceipt.ui.theme.BQSqwadDataReceiptTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        const val EXTRA_OPEN_LIVE_BUBBLE = "open_live_bubble_prompt"
    }

    @Inject
    lateinit var consentManager: ConsentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Request UMP consent, then initialize ads
        consentManager.requestConsent(this) { canRequest ->
            if (canRequest) {
                (application as DataReceiptApp).initializeAdsIfAllowed()
            }
        }

        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val theme by viewModel.theme.collectAsStateWithLifecycle()
            BQSqwadDataReceiptTheme(appTheme = theme) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavHost(openLiveBubblePrompt = intent.getBooleanExtra(EXTRA_OPEN_LIVE_BUBBLE, false))
                }
            }
        }
    }
}
