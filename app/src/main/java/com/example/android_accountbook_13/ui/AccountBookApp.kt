package com.example.android_accountbook_13.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_accountbook_13.ui.common.component.BottomAppBar
import com.example.android_accountbook_13.ui.theme.AccountBookTheme

@Composable
fun AccountBookApp() {
    AccountBookTheme {
        val appState = rememberAccountBookAppState()

        Scaffold(
            scaffoldState = appState.scaffoldState,
            bottomBar = {
                BottomAppBar(
                    navController = appState.navController,
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
                History.route,
                appState.accountBookViewModel,
                appState.settingViewModel
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    AccountBookApp()
}