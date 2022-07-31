package com.example.android_accountbook_13.presenter.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.presenter.calendar.CalendarScreen
import com.example.android_accountbook_13.presenter.component.AccountBookAdditionScreen
import com.example.android_accountbook_13.presenter.history.HistoryScreen
import com.example.android_accountbook_13.presenter.setting.SettingScreen
import com.example.android_accountbook_13.presenter.statistic.StatisticScreen

@Composable
fun AccountBookNavHost(
    navController: NavHostController,
    innerPaddingModifier: PaddingValues,
    startDestination: String = History.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(innerPaddingModifier)
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
            SettingScreen(navController)
        }

        composable(
            route = Addition.route,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType},
                navArgument("id") { type = NavType.IntType},
                navArgument("type"){ type = NavType.BoolType}
            )
        ) {
            AccountBookAdditionScreen(
                navController,
                title = it.arguments?.getString("title")!!,
                id = it.arguments?.getInt("id"),
                type = it.arguments?.getBoolean("type")!!
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

object Addition : AccountBookDestination {
    override val route: String = "addition/{title},{id},{type}"
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