package com.example.android_accountbook_13.presenter.history

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_accountbook_13.data.DummyData
import com.example.android_accountbook_13.data.HistoryItem
import com.example.android_accountbook_13.presenter.component.AccountBookFab
import com.example.android_accountbook_13.presenter.component.AccountBookFilterButton
import com.example.android_accountbook_13.presenter.component.AccountBookHistoryItemContent
import com.example.android_accountbook_13.presenter.component.AccountBookHistoryItemHeader
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.ui.theme.Purple

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
        var leftChecked by rememberSaveable { mutableStateOf(true) }
        var rightChecked by rememberSaveable { mutableStateOf(true) }
        var leftMoney by rememberSaveable { mutableStateOf(0L) }
        var rightMoney by rememberSaveable { mutableStateOf(0L) }

        Column {
            AccountBookFilterButton(
                leftChecked = leftChecked,
                rightChecked = rightChecked,
                leftMoney = leftMoney,
                rightMoney = rightMoney,
                onLeftClick = { leftChecked = !leftChecked },
                onRightClick = { rightChecked = !rightChecked },
                onLeftCheckedChange = { leftChecked = !leftChecked },
                onRightCheckedChange = { rightChecked = !rightChecked },
            )
            // TODO: This ideally would be done in the ViewModel
            val grouped = DummyData.historyItem.groupBy { it.payment.day }
            var totalIncome = 0L
            var totalExpense = 0L
            LazyColumn {
                grouped.forEach { (day, historyList) ->
                    var income = 0L
                    var expense = 0L
                    for (item in historyList) {
                        if (item.payment.methodType) {
                            income = item.payment.money
                            totalIncome = item.payment.money
                        } else {
                            expense += item.payment.money
                            totalExpense += item.payment.money
                        }
                    }

                    item {
                        AccountBookHistoryItemHeader(
                            date = "7월 ${day}일",
                            income = income,
                            expense = expense
                        )
                    }

                    val historyItems: MutableList<HistoryItem> = historyList.toMutableList()
                    val lastHistoryItem = historyItems[historyItems.lastIndex]
                    historyItems.removeLast()

                    items(historyItems) { item ->
                        AccountBookHistoryItemContent(
                            item,
                            onClick = { /*TODO*/ }) {
                            true
                        }
                        Divider(color = LightPurple, modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp))
                    }

                    item {
                        AccountBookHistoryItemContent(
                            lastHistoryItem,
                            onClick = { /*TODO*/ }) {
                            true
                        }
                    }

                    item {
                        Divider(color = Purple, modifier = Modifier.padding(top = 8.dp))
                    }
                }
            }
            leftMoney = totalIncome
            rightMoney = totalExpense
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryPreview() {
    HistoryScreen()
}