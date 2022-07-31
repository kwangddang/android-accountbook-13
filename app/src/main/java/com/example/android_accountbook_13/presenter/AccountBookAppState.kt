package com.example.android_accountbook_13.presenter

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_accountbook_13.presenter.navigation.AccountBookDestination
import com.example.android_accountbook_13.presenter.navigation.bottomTabScreens

@Composable
fun rememberAccountBookAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    title: MutableState<String> = rememberSaveable { mutableStateOf("")}
) =
    remember(scaffoldState, navController) {
        AccountBookAppState(scaffoldState, navController, title)
    }

@Stable
class AccountBookAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    var title: MutableState<String>
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