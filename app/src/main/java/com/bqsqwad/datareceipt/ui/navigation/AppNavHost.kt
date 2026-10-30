package com.bqsqwad.datareceipt.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ui.screens.home.HomeScreen
import com.bqsqwad.datareceipt.ui.screens.apps.AppsScreen
import com.bqsqwad.datareceipt.ui.screens.tools.ToolsScreen
import com.bqsqwad.datareceipt.ui.screens.receipts.ReceiptsScreen
import com.bqsqwad.datareceipt.ui.screens.settings.SettingsScreen

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

    Scaffold(
        bottomBar = {
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
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Apps.route) { AppsScreen() }
            composable(Screen.Tools.route) { ToolsScreen() }
            composable(Screen.Receipts.route) { ReceiptsScreen() }
            composable(Screen.Settings.route) { SettingsScreen(navController) }
        }
    }
}
