package com.example.android_accountbook_13.presenter

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.presenter.component.AccountBookBottomAppBar
import com.example.android_accountbook_13.presenter.component.AccountBookTopAppBar
import com.example.android_accountbook_13.presenter.navigation.AccountBookNavHost
import com.example.android_accountbook_13.ui.theme.AccountBookTheme

@Composable
fun AccountBookApp() {
    AccountBookTheme {
        val appState = rememberAccountBookAppState()

        Scaffold(
            scaffoldState = appState.scaffoldState,
            topBar = {
                AccountBookTopAppBar(
                    title = "",
                    leftVectorResource = R.drawable.ic_left,
                    rightVectorResource = R.drawable.ic_right,
                    onLeftClick = {},
                    onRightClick = {}
                )
            },
            bottomBar = {
                AccountBookBottomAppBar(
                    destination = appState.currentScreen ?: History,
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