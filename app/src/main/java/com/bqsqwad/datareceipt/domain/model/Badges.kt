package com.bqsqwad.datareceipt.domain.model

/**
 * Cosmetic badges and streaks only.
 * No points balance, no cash-out, no airtime/data rewards, no prizes.
 */
data class Badge(
    val id: String,
    val title: String,
    val description: String,
    val earned: Boolean = false
)

data class Streak(
    val type: String,
    val currentDays: Int,
    val bestDays: Int
)

object BadgeCatalog {
    val all = listOf(
        Badge("first_receipt", "First Receipt", "Created your first Data Receipt"),
        Badge("week_under_budget", "Under Budget", "Stayed under your Data Budget for a week"),
        Badge("wifi_hero", "Wi-Fi Hero", "Saved a significant amount by using Wi-Fi"),
        Badge("leak_hunter", "Leak Hunter", "Identified and reviewed background data use"),
        Badge("calibrated", "Calibrated", "Matched the app to your carrier balance")
    )
}
