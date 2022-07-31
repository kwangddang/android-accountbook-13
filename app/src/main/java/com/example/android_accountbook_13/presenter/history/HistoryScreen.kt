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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.data.dto.AccountBookItem
import com.example.android_accountbook_13.presenter.component.*
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.ui.theme.Purple
import com.example.android_accountbook_13.utils.decreaseDate
import com.example.android_accountbook_13.utils.getDateString
import com.example.android_accountbook_13.utils.increaseDate

@Composable
fun HistoryScreen(
    historyViewModel: HistoryViewModel = hiltViewModel(),
) {

    var date by historyViewModel.date
    historyViewModel.getAccountBookItems(date.year, date.month)

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
            HistoryFab(onClick = {})
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        val checkedItems by historyViewModel.checkedItems.collectAsState()
        var incomeChecked by rememberSaveable { mutableStateOf(true) }
        var expenseChecked by rememberSaveable { mutableStateOf(true) }
        val incomeMoney by historyViewModel.incomeMoney.collectAsState()
        val expenseMoney by historyViewModel.expenseMoney.collectAsState()
        historyViewModel.getCheckedItems(incomeChecked,expenseChecked)
        val group = checkedItems.groupBy { it.history.day }

        Column {
            AccountBookFilterButton(
                incomeChecked = incomeChecked,
                expenseChecked = expenseChecked,
                incomeMoney = incomeMoney,
                expenseMoney = expenseMoney,
                onIncomeClick = {
                    incomeChecked = !incomeChecked
                },
                onExpenseClick = {
                    expenseChecked = !expenseChecked
                },
                onIncomeCheckedChange = { incomeChecked = !incomeChecked },
                onExpenseCheckedChange = { expenseChecked = !expenseChecked },
            )
            if (checkedItems.isNotEmpty()) {
                LazyColumn {
                    group.forEach { (day, historyList) ->
                        var income = 0L
                        var expense = 0L
                        for (item in historyList) {
                            if (item.history.methodType == 1) {
                                income += item.history.money
                            } else {
                                expense += item.history.money
                            }
                        }

                        item {
                            AccountBookItemHeader(
                                date = "${date.month}월 ${day}일",
                                income = income,
                                expense = expense,
                                incomeChecked,
                                expenseChecked
                            )
                        }

                        val accountBookItems: MutableList<AccountBookItem> = historyList.toMutableList()
                        val lastAccountBookItem = accountBookItems.removeLast()

                        items(accountBookItems) { item ->
                            AccountBookItemContent(
                                item,
                                onClick = { /*TODO*/ }) {
                                true
                            }
                            Divider(color = LightPurple, modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp))
                        }

                        item {
                            AccountBookItemContent(
                                lastAccountBookItem,
                                onClick = { /*TODO*/ }) {
                                true
                            }
                        }

                        item {
                            Divider(color = Purple, modifier = Modifier.padding(top = 8.dp))
                        }

                    }
                }
            } else {
                BlankScreen()
            }
        }
    }
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