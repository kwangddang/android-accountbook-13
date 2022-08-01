package com.example.android_accountbook_13.presenter

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_accountbook_13.presenter.component.BottomAppBar
import com.example.android_accountbook_13.presenter.navigation.AccountBookNavHost
import com.example.android_accountbook_13.presenter.navigation.History
import com.example.android_accountbook_13.presenter.navigation.navigateSingleTopTo
import com.example.android_accountbook_13.ui.theme.AccountBookTheme

@Composable
fun AccountBookApp() {
    AccountBookTheme {
        val appState = rememberAccountBookAppState()

        Scaffold(
            scaffoldState = appState.scaffoldState,
            bottomBar = {
                BottomAppBar(
                    destination = appState.currentScreen,
                    onClick = { newScreen ->
                        appState.navController.navigateSingleTopTo(newScreen.route)
                    }
                )
            }
        ) { innerPadding ->
            AccountBookNavHost(
                appState.navController,
                innerPadding,
                History.route
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    AccountBookApp()
}