package com.example.android_accountbook_13.presenter

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_accountbook_13.R

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

interface AccountBookDestination {
    val route: String
    val content: String
    val vectorResource: Int
}

object History : AccountBookDestination {
    override val route: String = "history"
    override val content: String = "내역"
    override val vectorResource = R.drawable.ic_history
}

object Calendar : AccountBookDestination {
    override val route: String = "calendar"
    override val content: String = "달력"
    override val vectorResource = R.drawable.ic_calendar
}

object Statistic : AccountBookDestination {
    override val route: String = "statistic"
    override val content: String = "통계"
    override val vectorResource = R.drawable.ic_statistic
}

object Setting : AccountBookDestination {
    override val route: String = "setting"
    override val content: String = "설정"
    override val vectorResource = R.drawable.ic_setting
}

val bottomTabScreens = listOf(History, Calendar, Statistic, Setting)

fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}