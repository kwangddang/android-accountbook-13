package com.example.android_accountbook_13.presenter

import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.presenter.calendar.CalendarScreen
import com.example.android_accountbook_13.presenter.component.BottomAppBars
import com.example.android_accountbook_13.presenter.component.TopAppBars
import com.example.android_accountbook_13.presenter.history.HistoryScreen
import com.example.android_accountbook_13.ui.theme.MyTheme

@Composable
fun MainScreen() {
    MyTheme() {
        Scaffold(
            topBar = { TopAppBars("2022년 7월", R.drawable.ic_left, R.drawable.ic_right) },
            bottomBar = { BottomAppBars {} },
        ) {
            HistoryScreen()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen()
}