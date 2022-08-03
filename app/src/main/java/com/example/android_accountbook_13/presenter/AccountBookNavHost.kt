package com.example.android_accountbook_13.presenter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.presenter.calendar.CalendarScreen
import com.example.android_accountbook_13.presenter.common.AddingScreen
import com.example.android_accountbook_13.presenter.history.AddingHistoryScreen
import com.example.android_accountbook_13.presenter.history.HistoryScreen
import com.example.android_accountbook_13.presenter.history.HistoryViewModel
import com.example.android_accountbook_13.presenter.setting.SettingScreen
import com.example.android_accountbook_13.presenter.setting.SettingViewModel
import com.example.android_accountbook_13.presenter.statistic.StatisticScreen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AccountBookNavHost(
    navController: NavHostController,
    innerPaddingModifier: PaddingValues,
    startDestination: String = History.route,
    historyViewModel: HistoryViewModel,
    settingViewModel: SettingViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(innerPaddingModifier)
    ) {
        composable(History.route) {
            HistoryScreen(navController, historyViewModel)
        }

        composable(Calendar.route) {
            CalendarScreen(historyViewModel)
        }

        composable(Statistic.route) {
            StatisticScreen(historyViewModel)
        }

        composable(Setting.route) {
            SettingScreen(navController, settingViewModel)
        }

        composable(
            route = AddingSetting.route,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("id") { type = NavType.IntType },
                navArgument("type") { type = NavType.BoolType }
            )
        ) {
            AddingScreen(
                navController,
                title = it.arguments?.getString("title")!!,
                name = it.arguments?.getString("name")!!,
                id = it.arguments?.getInt("id"),
                type = it.arguments?.getBoolean("type")!!,
                settingViewModel
            )
        }

        composable(
            route = AddingHistory.route,
            arguments = listOf(
                navArgument("method") { type = NavType.IntType },
            )
        ) {
            AddingHistoryScreen(
                navController,
                method = it.arguments?.getInt("method")!!,
                historyViewModel,
                settingViewModel
            )
        }
    }
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

object AddingSetting : AccountBookDestination {
    override val route: String = "addingSetting?title={title}&name={name}&id={id}&type={type}"
    override val content: String = "추가"
    override val vectorResource: Int = 0
}

object AddingHistory : AccountBookDestination {
    override val route: String = "addingHistory/{method}"
    override val content: String = "추가"
    override val vectorResource: Int = 0
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