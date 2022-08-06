package com.example.android_accountbook_13.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_accountbook_13.ui.accountbook.AccountBookViewModel
import com.example.android_accountbook_13.ui.setting.SettingViewModel

@Composable
fun rememberAccountBookAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    accountBookViewModel: AccountBookViewModel = hiltViewModel(),
    settingViewModel: SettingViewModel = hiltViewModel()
) =
    remember(scaffoldState, navController) {
        AccountBookAppState(scaffoldState, navController, accountBookViewModel, settingViewModel)
    }

@Stable
class AccountBookAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val accountBookViewModel: AccountBookViewModel,
    val settingViewModel: SettingViewModel
) {
    private val currentBackStack: NavBackStackEntry?
        @Composable get() =
            navController.currentBackStackEntryAsState().value

    private val currentDestination: NavDestination?
        @Composable get() =
            currentBackStack?.destination

    val currentScreen: AccountBookDestination?
        @Composable get() =
            bottomTabScreens.find { it.route == currentDestination?.route }
}