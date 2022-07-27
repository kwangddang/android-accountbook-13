package com.example.android_accountbook_13.presenter.history

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_accountbook_13.presenter.component.AccountBookFab
import com.example.android_accountbook_13.ui.theme.Pink1

@Composable
fun HistoryScreen() {
    androidx.compose.material.Surface() {
        
    }
    Scaffold(
        floatingActionButton = {
            AccountBookFab (onClick = {})
        },
        backgroundColor = MaterialTheme.colors.background
    ) {

    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryPreview() {
    HistoryScreen()
}