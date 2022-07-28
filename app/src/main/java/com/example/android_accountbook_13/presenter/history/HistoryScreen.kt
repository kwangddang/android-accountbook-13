package com.example.android_accountbook_13.presenter.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.data.DummyData
import com.example.android_accountbook_13.data.HistoryItem
import com.example.android_accountbook_13.presenter.component.*
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.ui.theme.Purple
import com.example.android_accountbook_13.utils.decreaseDate
import com.example.android_accountbook_13.utils.getCurrentDate
import com.example.android_accountbook_13.utils.getDateString
import com.example.android_accountbook_13.utils.increaseDate

@Composable
fun HistoryScreen() {
    var date by remember { mutableStateOf(getCurrentDate()) }

    Scaffold(
        topBar = {
            AccountBookTopAppBar(
                title = getDateString(date),
                leftVectorResource = R.drawable.ic_left,
                rightVectorResource = R.drawable.ic_right,
                onLeftClick = {
                    date = decreaseDate(date)
                },
                onRightClick = {
                    date = increaseDate(date)
                }
            )
        },
        floatingActionButton = {
            AccountBookFab(onClick = {})
        },
        backgroundColor = MaterialTheme.colors.background
    ) {

        var leftChecked by rememberSaveable { mutableStateOf(true) }
        var rightChecked by rememberSaveable { mutableStateOf(true) }
        var leftMoney by rememberSaveable { mutableStateOf(0L) }
        var rightMoney by rememberSaveable { mutableStateOf(0L) }

        var totalIncome = 0L
        var totalExpense = 0L

        val checkedList = mutableListOf<HistoryItem>()

        /*TODO Domain 영역에서 처리?*/
        val pair = willDomain(totalIncome, totalExpense, leftChecked, rightChecked, checkedList)
        totalExpense = pair.first
        totalIncome = pair.second

        /*TODO ViewModel에서 처리?*/
        val group = willViewModel(checkedList)

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
            if (checkedList.isNotEmpty()) {
                LazyColumn {
                    group.forEach { (day, historyList) ->
                        var income = 0L
                        var expense = 0L
                        for (item in historyList) {
                            if (item.payment.methodType) {
                                income += item.payment.money
                            } else {
                                expense += item.payment.money
                            }
                        }
                        /**
                         * Header
                         */
                        item {
                            AccountBookHistoryItemHeader(
                                date = "7월 ${day}일",
                                income = income,
                                expense = expense,
                                leftChecked,
                                rightChecked
                            )
                        }

                        val historyItems: MutableList<HistoryItem> = historyList.toMutableList()
                        val lastHistoryItem = historyItems.removeLast()

                        /**
                         * Content
                         */
                        items(historyItems) { item ->
                            AccountBookHistoryItemContent(
                                item,
                                onClick = { /*TODO*/ }) {
                                true
                            }
                            Divider(color = LightPurple, modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp))
                        }

                        /**
                         * LastItem
                         */

                        item {
                            AccountBookHistoryItemContent(
                                lastHistoryItem,
                                onClick = { /*TODO*/ }) {
                                true
                            }
                        }

                        /**
                         * Footer
                         */
                        item {
                            Divider(color = Purple, modifier = Modifier.padding(top = 8.dp))
                        }

                    }
                }
            } else {
                BlankScreen()
            }
            leftMoney = totalIncome
            rightMoney = totalExpense
        }
    }
}

@Composable
private fun willViewModel(checkedList: MutableList<HistoryItem>): Map<Int, List<HistoryItem>> {
    return checkedList.groupBy { it.payment.day }
}

@Composable
private fun willDomain(
    totalIncome: Long,
    totalExpense: Long,
    leftChecked: Boolean,
    rightChecked: Boolean,
    checkedList: MutableList<HistoryItem>
): Pair<Long, Long> {
    var totalIncome1 = totalIncome
    var totalExpense1 = totalExpense
    DummyData.historyItem.forEach { item ->
        if (item.payment.methodType) {
            totalIncome1 += item.payment.money
        } else {
            totalExpense1 += item.payment.money
        }
        if ((item.payment.methodType && leftChecked) || !item.payment.methodType && rightChecked) {
            checkedList.add(item)
        }
    }
    return Pair(totalExpense1, totalIncome1)
}


@Composable
private fun BlankScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "내역이 없습니다.",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryPreview() {
    HistoryScreen()
}