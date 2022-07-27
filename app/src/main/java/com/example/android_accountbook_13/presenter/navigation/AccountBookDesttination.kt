package com.example.android_accountbook_13.presenter.navigation

import com.example.android_accountbook_13.R

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