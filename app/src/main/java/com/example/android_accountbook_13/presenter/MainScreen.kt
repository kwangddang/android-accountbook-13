package com.example.android_accountbook_13.presenter

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.presenter.component.AccountBookBottomAppBar
import com.example.android_accountbook_13.presenter.component.AccountBookTopAppBar
import com.example.android_accountbook_13.presenter.navigation.AccountBookNavHost
import com.example.android_accountbook_13.presenter.navigation.History
import com.example.android_accountbook_13.presenter.navigation.bottomTabScreens
import com.example.android_accountbook_13.presenter.navigation.navigateSingleTopTo
import com.example.android_accountbook_13.ui.theme.MyTheme

@Composable
fun MainScreen() {
    MyTheme() {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen = bottomTabScreens.find { it.route ==  currentDestination?.route}

        Scaffold(
            topBar = { AccountBookTopAppBar("2022년 7월", R.drawable.ic_left, R.drawable.ic_right) },
            bottomBar = {
                AccountBookBottomAppBar(
                    destination = currentScreen ?: History,
                    onClick = { newScreen ->
                        navController.navigateSingleTopTo(newScreen.route)
                    }
                )
            }
        ) {
            AccountBookNavHost(
                navController,
                History.route
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen()
}