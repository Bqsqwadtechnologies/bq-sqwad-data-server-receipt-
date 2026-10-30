package com.bqsqwad.datareceipt.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

enum class AppTheme {
    SKY_LIGHT,
    CLOUD_WHITE,
    OCEAN_MIST,
    SKY_NIGHT,
    AMOLED_SKY,
    SUNRISE_SKY,
    FOLLOW_SYSTEM
}

private val SkyLightScheme = lightColorScheme(
    primary = SkyPrimary,
    onPrimary = Color.White,
    primaryContainer = SkyTint,
    onPrimaryContainer = SkyInk,
    secondary = SkyPrimaryDark,
    onSecondary = Color.White,
    background = SkyBackground,
    onBackground = SkyInk,
    surface = SkySurface,
    onSurface = SkyInk,
    surfaceVariant = SkyTint,
    onSurfaceVariant = SkyMuted,
    outline = SkyOutline,
    error = SkyDanger
)

private val SkyNightScheme = darkColorScheme(
    primary = NightPrimary,
    onPrimary = NightBackground,
    primaryContainer = NightSurface,
    onPrimaryContainer = NightInk,
    background = NightBackground,
    onBackground = NightInk,
    surface = NightSurface,
    onSurface = NightInk,
    surfaceVariant = NightSurface,
    onSurfaceVariant = Color(0xFFA0B4C8),
    outline = Color(0xFF2A4055),
    error = SkyDanger
)

private val AmoledScheme = darkColorScheme(
    primary = NightPrimary,
    onPrimary = Color.Black,
    primaryContainer = AmoledSurface,
    onPrimaryContainer = Color.White,
    background = AmoledBackground,
    onBackground = Color.White,
    surface = AmoledSurface,
    onSurface = Color.White,
    surfaceVariant = AmoledSurface,
    onSurfaceVariant = Color(0xFFA0B4C8),
    outline = Color(0xFF333333),
    error = SkyDanger
)

@Composable
fun BQSqwadDataReceiptTheme(
    appTheme: AppTheme = AppTheme.SKY_LIGHT,
    useMaterialYou: Boolean = false,
    content: @Composable () -> Unit
) {
    val darkTheme = when (appTheme) {
        AppTheme.SKY_NIGHT, AppTheme.AMOLED_SKY -> true
        AppTheme.FOLLOW_SYSTEM -> isSystemInDarkTheme()
        else -> false
    }

    val colorScheme = when (appTheme) {
        AppTheme.SKY_NIGHT -> SkyNightScheme
        AppTheme.AMOLED_SKY -> AmoledScheme
        AppTheme.FOLLOW_SYSTEM -> if (isSystemInDarkTheme()) SkyNightScheme else SkyLightScheme
        else -> SkyLightScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// Placeholder for Color import fix
import androidx.compose.ui.graphics.Color
