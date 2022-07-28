package com.example.android_accountbook_13.presenter.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_accountbook_13.data.DummyData
import com.example.android_accountbook_13.presenter.component.AccountBookFab
import com.example.android_accountbook_13.presenter.component.AccountBookHistoryItemContent
import com.example.android_accountbook_13.presenter.component.AccountBookHistoryItemHeader

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryScreen() {
    Surface() {

    }
    Scaffold(
        floatingActionButton = {
            AccountBookFab(onClick = {})
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        /**
         * 수입 지출 표시, 목록 아이템 순서 이상해지는 거 수정, 날이 바뀔 때 디바이더 추가
         */
        // TODO: This ideally would be done in the ViewModel
        val grouped = DummyData.historyItem.groupBy { it.payment.day }

        LazyColumn {
            grouped.forEach { (day, historyItem) ->
                var income = 0L
                var expense = 0L
                for(item in historyItem) {
                    if(item.payment.methodType) income += item.payment.money
                    else expense += item.payment.money
                }

                item{
                    AccountBookHistoryItemHeader(
                        date = "7월 ${day}일",
                        income = income,
                        expense = expense
                    )
                }

                items(historyItem) { item ->
                    AccountBookHistoryItemContent(
                        item,
                        onClick = { /*TODO*/ }) {
                        true
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryPreview() {
    HistoryScreen()
}