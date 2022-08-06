package com.example.android_accountbook_13.ui.accountbook.history

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.data.dto.AccountBookItem
import com.example.android_accountbook_13.ui.accountbook.AccountBookViewModel
import com.example.android_accountbook_13.ui.accountbook.history.component.HistoryFab
import com.example.android_accountbook_13.ui.common.component.*
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.ui.theme.Purple
import com.example.android_accountbook_13.utils.*

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HistoryScreen(
    navHostController: NavHostController,
    accountBookViewModel: AccountBookViewModel,
) {
    val context = LocalContext.current
    val date by accountBookViewModel.date
    var incomeChecked by rememberSaveable { mutableStateOf(true) }
    var expenseChecked by rememberSaveable { mutableStateOf(true) }
    var isEditMode by rememberSaveable { mutableStateOf(false) }
    val deleteIdList = rememberSaveable { mutableListOf<Int>()}
    var isDialog by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = if (isEditMode) stringResource(id = R.string.multiple_selection) else getYearMonthString(date),
                titleOnClick = {if(!isEditMode) isDialog = true},
                leftVectorResource = if (isEditMode) R.drawable.ic_back else R.drawable.ic_left,
                rightVectorResource = if (isEditMode) R.drawable.ic_trash else R.drawable.ic_right,
                onLeftClick = {
                    if(isEditMode) {
                        isEditMode = false
                        deleteIdList.clear()
                    }
                    else {
                        accountBookViewModel.decreaseDate()
                    }
                },
                onRightClick = {
                    if(isEditMode) {
                        accountBookViewModel.deleteHistory(deleteIdList,{ message ->
                            showToast(context, message)
                        },{
                            accountBookViewModel.getAccountBookItems()
                        })
                        isEditMode = false
                        deleteIdList.clear()
                    }
                    else {
                        accountBookViewModel.increaseDate()
                    }
                }
            )
        },
        floatingActionButton = {
            HistoryFab(onClick = {
                navHostController.navigate("addingHistory/${if ((incomeChecked && expenseChecked) || (!incomeChecked && !expenseChecked) || incomeChecked) 0 else 1}")
            })
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        accountBookViewModel.getCheckedItems(incomeChecked, expenseChecked)
        val incomeMoney by accountBookViewModel.incomeMoney
        val expenseMoney by accountBookViewModel.expenseMoney
        val group = accountBookViewModel.checkedItems.value.groupBy { it.history.day }
        if (isDialog) {
            Dialog(onDismissRequest = { isDialog = false }) {
                YearMonthDatePicker(onDismissRequest = { isDialog = false }) { year, month ->
                    accountBookViewModel.changeDate(year,month)
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
                    if(!isEditMode) incomeChecked = !incomeChecked
                },
                onExpenseClick = {
                    if(!isEditMode) expenseChecked = !expenseChecked
                },
                onIncomeCheckedChange = { if(!isEditMode) incomeChecked = !incomeChecked },
                onExpenseCheckedChange = { if(!isEditMode) expenseChecked = !expenseChecked },
            )
            if (group.isNotEmpty()) {
                LazyColumn {
                    group.forEach { (day, historyList) ->
                        item {
                            ItemHeader(
                                date = "${date.month}월 ${day}일",
                                income = accountBookViewModel.incomeMoneyOfDay[day] ?: 0,
                                expense = accountBookViewModel.expenseMoneyOfDay[day] ?: 0,
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
                                },
                                onClick = {
                                    accountBookViewModel.navItem = item
                                    navHostController.navigate("addingHistory/${item.history.methodType}")
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
                                    accountBookViewModel.navItem = lastAccountBookItem
                                    navHostController.navigate("addingHistory/${lastAccountBookItem.history.methodType}")
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
                    item{
                        Spacer(modifier = Modifier.size(96.dp))
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
            text = stringResource(id = R.string.no_history),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}