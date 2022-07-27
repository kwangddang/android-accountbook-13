package com.example.android_accountbook_13.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_accountbook_13.presenter.calendar.CalendarScreen
import com.example.android_accountbook_13.presenter.history.HistoryScreen
import com.example.android_accountbook_13.presenter.setting.SettingScreen
import com.example.android_accountbook_13.presenter.statistic.StatisticScreen

@Composable
fun AccountBookNavHost(
    navController: NavHostController,
    startDestination: String = History.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(History.route) {
            HistoryScreen()
        }

        composable(Calendar.route) {
            CalendarScreen()
        }

        composable(Statistic.route) {
            StatisticScreen()
        }

        composable(Setting.route) {
            SettingScreen()
        }
    }
}

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