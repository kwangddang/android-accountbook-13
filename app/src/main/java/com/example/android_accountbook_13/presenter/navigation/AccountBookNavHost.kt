package com.example.android_accountbook_13.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_accountbook_13.presenter.Calendar
import com.example.android_accountbook_13.presenter.History
import com.example.android_accountbook_13.presenter.Setting
import com.example.android_accountbook_13.presenter.Statistic
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