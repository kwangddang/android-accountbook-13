package com.example.android_accountbook_13.presenter.navigation

import androidx.compose.ui.res.stringResource
import com.example.android_accountbook_13.R

interface Destination {
    val route: String
}

object History : Destination {
    override val route: String = "history"
}

object Calendar : Destination {
    override val route: String = "calendar"
}

object Statistic : Destination {
    override val route: String = "statistic"
}

object Setting : Destination {
    override val route: String = "setting"
}