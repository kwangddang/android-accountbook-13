package com.example.android_accountbook_13.presenter.history

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_accountbook_13.data.model.Payment
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
        //Test Code
//        val temp = listOf<Payment>()
//        var isHeader = true
//        var prevDay = 0
//        for(i in temp) {
//            if(prevDay != i.day)
//                isHeader = true
//            isHeader = false
//        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryPreview() {
    HistoryScreen()
}