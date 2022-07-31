package com.example.android_accountbook_13.presenter.setting

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.android_accountbook_13.presenter.component.AccountBookTopAppBar

@Composable
fun SettingScreen() {
    Scaffold(
        topBar = {
            AccountBookTopAppBar(
                title = "설정",
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
    }
}