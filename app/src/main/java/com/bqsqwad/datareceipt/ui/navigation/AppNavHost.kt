package com.bqsqwad.datareceipt.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ui.MainViewModel
import com.bqsqwad.datareceipt.ui.screens.about.AboutScreen
import com.bqsqwad.datareceipt.ui.screens.apps.AppsScreen
import com.bqsqwad.datareceipt.ui.screens.home.HomeScreen
import com.bqsqwad.datareceipt.ui.screens.legal.LegalScreen
import com.bqsqwad.datareceipt.ui.screens.legal.LegalType
import com.bqsqwad.datareceipt.ui.screens.more.MoreFromScreen
import com.bqsqwad.datareceipt.ui.screens.onboarding.OnboardingScreen
import com.bqsqwad.datareceipt.ui.screens.receipts.ReceiptsScreen
import com.bqsqwad.datareceipt.ui.screens.settings.CountryCurrencyScreen
import com.bqsqwad.datareceipt.ui.screens.settings.SettingsScreen
import com.bqsqwad.datareceipt.ui.screens.theme.ThemePickerScreen
import com.bqsqwad.datareceipt.ui.screens.tools.CalculatorScreen
import com.bqsqwad.datareceipt.ui.screens.tools.HotspotSplitScreen
import com.bqsqwad.datareceipt.ui.screens.tools.LiveBubbleScreen
import com.bqsqwad.datareceipt.ui.screens.tools.ToolsScreen

sealed class Screen(val route: String, val labelRes: Int, val selectedIcon: ImageVector, val unselectedIcon: ImageVector) {
    data object Home : Screen("home", R.string.nav_home, Icons.Filled.Home, Icons.Outlined.Home)
    data object Apps : Screen("apps", R.string.nav_apps, Icons.Filled.Apps, Icons.Outlined.Apps)
    data object Tools : Screen("tools", R.string.nav_tools, Icons.Filled.Build, Icons.Outlined.Build)
    data object Receipts : Screen("receipts", R.string.nav_receipts, Icons.Filled.Receipt, Icons.Outlined.Receipt)
    data object Settings : Screen("settings", R.string.nav_settings, Icons.Filled.Settings, Icons.Outlined.Settings)
}

val bottomNavItems = listOf(Screen.Home, Screen.Apps, Screen.Tools, Screen.Receipts, Screen.Settings)

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val mainViewModel: MainViewModel = hiltViewModel()
    val onboardingDone by mainViewModel.isOnboardingDone.collectAsStateWithLifecycle()

    val startDestination = if (onboardingDone) Screen.Home.route else Routes.ONBOARDING
    val showBottomBar = currentDestination?.route in bottomNavItems.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { screen ->
                        val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                        NavigationBarItem(
                            icon = { Icon(if (selected) screen.selectedIcon else screen.unselectedIcon, contentDescription = null) },
                            label = { Text(stringResource(screen.labelRes)) },
                            selected = selected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.ONBOARDING) { OnboardingScreen(navController) }

            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Apps.route) { AppsScreen() }
            composable(Screen.Tools.route) { ToolsScreen(navController) }
            composable(Screen.Receipts.route) { ReceiptsScreen() }
            composable(Screen.Settings.route) { SettingsScreen(navController) }

            composable(Routes.THEME_PICKER) { ThemePickerScreen(navController) }
            composable(Routes.PRIVACY) { LegalScreen(LegalType.PRIVACY, navController) }
            composable(Routes.TERMS) { LegalScreen(LegalType.TERMS, navController) }
            composable(Routes.COPYRIGHT) { LegalScreen(LegalType.COPYRIGHT, navController) }
            composable(Routes.MORE_FROM) { MoreFromScreen(navController) }
            composable(Routes.ABOUT) { AboutScreen(navController) }
            composable(Routes.HELP) { LegalScreen(LegalType.PRIVACY, navController) }
            composable(Routes.COUNTRY_PICKER) { CountryCurrencyScreen(navController) }
            composable(Routes.BUNDLE_EDITOR) { ThemePickerScreen(navController) }

            composable(Routes.LIVE_BUBBLE) { LiveBubbleScreen(navController) }
            composable(Routes.CALCULATOR) { CalculatorScreen(navController) }
            composable(Routes.HOTSPOT) { HotspotSplitScreen(navController) }
            composable(Routes.PLAN_FINDER) { ToolsPlaceholder("Best Plan Finder", navController) }
            composable(Routes.BUDGET) { ToolsPlaceholder("Data Budget", navController) }
            composable(Routes.CALIBRATE) { ToolsPlaceholder("Calibrate", navController) }
            composable(Routes.LEAK_DETECTOR) { ToolsPlaceholder("Leak Detector", navController) }
            composable(Routes.TRAVEL) { ToolsPlaceholder("Travel Mode", navController) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ToolsPlaceholder(title: String, navController: androidx.navigation.NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(Modifier.padding(padding).padding(24.dp)) {
            Text("$title is connected. Full feature implementation continues.")
        }
    }
}
