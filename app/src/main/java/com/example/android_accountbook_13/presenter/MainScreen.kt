package com.example.android_accountbook_13.presenter

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.presenter.calendar.CalendarScreen
import com.example.android_accountbook_13.presenter.component.BottomAppBars
import com.example.android_accountbook_13.presenter.component.TopAppBars
import com.example.android_accountbook_13.presenter.history.HistoryScreen
import com.example.android_accountbook_13.presenter.navigation.*
import com.example.android_accountbook_13.presenter.setting.SettingScreen
import com.example.android_accountbook_13.presenter.statistic.StatisticScreen
import com.example.android_accountbook_13.ui.theme.MyTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    MyTheme() {
        val currentScreen: Destination by remember { mutableStateOf(History) }
        val navController = rememberNavController()

        Scaffold(
            topBar = { TopAppBars("2022년 7월", R.drawable.ic_left, R.drawable.ic_right) },
            bottomBar = { BottomAppBars {} },
        ) {
            NavHost(
                navController = navController,
                startDestination = stringResource(R.string.nav_history)
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
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen()
}