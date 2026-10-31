package com.bqsqwad.datareceipt.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// System fallbacks until Poppins/Inter font files are bundled in res/font/
val PoppinsFamily = FontFamily.SansSerif
val InterFamily = FontFamily.SansSerif

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 48.sp
    ),
    displayMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelLarge = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
)
