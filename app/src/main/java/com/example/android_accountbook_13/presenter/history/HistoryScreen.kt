package com.example.android_accountbook_13.presenter.history

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.data.dto.AccountBookItem
import com.example.android_accountbook_13.presenter.component.*
import com.example.android_accountbook_13.presenter.history.component.HistoryFab
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.ui.theme.Purple
import com.example.android_accountbook_13.utils.Date
import com.example.android_accountbook_13.utils.decreaseDate
import com.example.android_accountbook_13.utils.getYearMonthString
import com.example.android_accountbook_13.utils.increaseDate

@Composable
fun HistoryScreen(
    navHostController: NavHostController,
    historyViewModel: HistoryViewModel = hiltViewModel(),
) {

    var date by historyViewModel.date
    historyViewModel.getAccountBookItems(date.year, date.month)
    var incomeChecked by rememberSaveable { mutableStateOf(true) }
    var expenseChecked by rememberSaveable { mutableStateOf(true) }

    var isEditMode by rememberSaveable { mutableStateOf(false) }
    val deleteIdList = rememberSaveable { mutableListOf<Int>()}
    var isDialog by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = if (isEditMode) "다중 선택" else getYearMonthString(date),
                titleOnClick = {if(!isEditMode) isDialog = true},
                leftVectorResource = if (isEditMode) R.drawable.ic_back else R.drawable.ic_left,
                rightVectorResource = if (isEditMode) R.drawable.ic_trash else R.drawable.ic_right,
                onLeftClick = {
                    if(isEditMode) {
                        isEditMode = false
                        deleteIdList.clear()
                    }
                    else date = decreaseDate(date)
                },
                onRightClick = {
                    if(isEditMode) {
                        historyViewModel.deleteHistory(deleteIdList)
                        isEditMode = false
                        deleteIdList.clear()
                    }
                    else date = increaseDate(date)
                }
            )
        },
        floatingActionButton = {
            HistoryFab(onClick = {
                navHostController.navigate(
                    "historyAddition/" +
                            "${if ((incomeChecked && expenseChecked) || (!incomeChecked && !expenseChecked) || incomeChecked) 0 else 1},-1"
                )
            })
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        val checkedItems by historyViewModel.checkedItems.collectAsState()
        val incomeMoney by historyViewModel.incomeMoney.collectAsState()
        val expenseMoney by historyViewModel.expenseMoney.collectAsState()
        historyViewModel.getCheckedItems(incomeChecked, expenseChecked)
        val group = checkedItems.groupBy { it.history.day }

        if (isDialog) {
            Dialog(onDismissRequest = { isDialog = false }) {
                YearMonthDayDatePicker(onDismissRequest = { isDialog = false }) { year, month, day ->
                    date = Date(year, month, day)
                    isDialog = false
                }
            }
        }

        Column {
            FilterButton(
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
                            ItemHeader(
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
                            HistoryItemContent(
                                item,
                                isEditMode = isEditMode,
                                onCheckedChange = { id ->
                                    if(deleteIdList.contains(id)) {
                                        deleteIdList.remove(id)
                                        if(deleteIdList.isEmpty()) isEditMode = false
                                    }
                                    else
                                        deleteIdList.add(id)
                                    Log.d("Test",deleteIdList.toString())
                                },
                                onClick = {
                                    navHostController.navigate("historyAddition/${item.history.methodType},${item.history.id}")
                                },
                                onCheckClick = { id ->
                                    if(deleteIdList.contains(id)) {
                                        deleteIdList.remove(id)
                                        if(deleteIdList.isEmpty()) isEditMode = false
                                    }
                                    else
                                        deleteIdList.add(id)
                                },
                                onLongClick = { id ->
                                    isEditMode = true
                                    if(deleteIdList.contains(id)) {
                                        deleteIdList.remove(id)
                                        if(deleteIdList.isEmpty()) isEditMode = false
                                    }
                                    else
                                        deleteIdList.add(id)
                                }
                            )
                            Divider(color = LightPurple, modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp))
                        }

                        item {
                            HistoryItemContent(
                                lastAccountBookItem,
                                isEditMode = isEditMode,
                                onClick = {
                                    navHostController.navigate("historyAddition/${lastAccountBookItem.history.methodType},${lastAccountBookItem.history.id}")
                                },
                                onCheckClick = { id ->
                                    if(deleteIdList.contains(id)) {
                                        deleteIdList.remove(id)
                                        if(deleteIdList.isEmpty()) isEditMode = false
                                    }
                                    else
                                        deleteIdList.add(id)
                                },
                                onCheckedChange = { id ->
                                    if(deleteIdList.contains(id)) {
                                        deleteIdList.remove(id)
                                        if(deleteIdList.isEmpty()) isEditMode = false
                                    }
                                    else
                                        deleteIdList.add(id)
                                },
                                onLongClick = { id ->
                                    isEditMode = true
                                    if(deleteIdList.contains(id)) {
                                        deleteIdList.remove(id)
                                        if(deleteIdList.isEmpty()) isEditMode = false
                                    }
                                    else
                                        deleteIdList.add(id)
                                }
                            )
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