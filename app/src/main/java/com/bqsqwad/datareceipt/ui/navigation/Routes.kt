package com.bqsqwad.datareceipt.ui.navigation

object Routes {
    const val HOME = "home"
    const val APPS = "apps"
    const val TOOLS = "tools"
    const val RECEIPTS = "receipts"
    const val SETTINGS = "settings"

    const val ONBOARDING = "onboarding"
    const val THEME_PICKER = "theme_picker"
    const val COUNTRY_PICKER = "country_picker"
    const val BUNDLE_EDITOR = "bundle_editor"
    const val ABOUT = "about"
    const val PRIVACY = "privacy"
    const val TERMS = "terms"
    const val COPYRIGHT = "copyright"
    const val MORE_FROM = "more_from"
    const val APP_DETAIL = "app_detail/{packageName}"
    const val RECEIPT_PREVIEW = "receipt_preview/{id}"
    const val LIVE_BUBBLE = "live_bubble"
    const val CALCULATOR = "calculator"
    const val PLAN_FINDER = "plan_finder"
    const val BUDGET = "budget"
    const val CALIBRATE = "calibrate"
    const val LEAK_DETECTOR = "leak_detector"
    const val HOTSPOT = "hotspot"
    const val TRAVEL = "travel"
    const val HELP = "help"
    const val DIAGNOSTICS = "diagnostics"

    fun appDetail(packageName: String) = "app_detail/$packageName"
    fun receiptPreview(id: String) = "receipt_preview/$id"
}
