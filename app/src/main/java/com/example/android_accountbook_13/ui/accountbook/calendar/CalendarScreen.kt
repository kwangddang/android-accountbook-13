package com.example.android_accountbook_13.ui.accountbook.calendar

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.ui.common.component.TopAppBar
import com.example.android_accountbook_13.ui.common.component.YearMonthDatePicker
import com.example.android_accountbook_13.ui.accountbook.AccountBookViewModel
import com.example.android_accountbook_13.ui.theme.*
import com.example.android_accountbook_13.utils.*

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CalendarScreen(
    accountBookViewModel: AccountBookViewModel
) {
    val date by accountBookViewModel.date
    var isDialog by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = getYearMonthString(date),
                titleOnClick = { isDialog = true },
                leftVectorResource = R.drawable.ic_left,
                rightVectorResource = R.drawable.ic_right,
                onLeftClick = accountBookViewModel::decreaseDate,
                onRightClick = accountBookViewModel::increaseDate
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        accountBookViewModel.getCheckedItems(true,true)
        if (isDialog) {
            Dialog(onDismissRequest = { isDialog = false }) {
                YearMonthDatePicker(onDismissRequest = { isDialog = false }) { year, month ->
                    accountBookViewModel.changeDate(year,month)
                    isDialog = false
                }
            }
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Calendar(date, accountBookViewModel)
            BothText(stringResource(id = R.string.income), longToMoneyUnit(accountBookViewModel.incomeMoney.value), Green6)
            Divider(modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp), color = LightPurple)
            BothText(stringResource(id = R.string.expense), "-"  + longToMoneyUnit(accountBookViewModel.expenseMoney.value), Red)
            Divider(modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp), color = LightPurple)
            BothText(stringResource(id = R.string.total), longToMoneyUnit(accountBookViewModel.incomeMoney.value - accountBookViewModel.expenseMoney.value), Purple)
            Divider(modifier = Modifier.padding(top = 8.dp), color = Purple)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(
    date: Date,
    viewModel: AccountBookViewModel
) {
    val calendar = getCalendar(date)
    LazyVerticalGrid(cells = GridCells.Fixed(7)) {
        calendar.forEach {
            item {
                Column(
                    modifier = Modifier
                        .border(0.5.dp, LightPurple)
                        .size(72.dp)
                        .padding(4.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    if (it.color == Purple) {
                        if (viewModel.incomeMoneyOfDay.containsKey(it.day) && viewModel.incomeMoneyOfDay[it.day]!! > 0L)
                            Text(
                                text = longToMoneyUnit(viewModel.incomeMoneyOfDay[it.day]!!),
                                fontSize = 10.sp,
                                color = Green6,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        if (viewModel.expenseMoneyOfDay.containsKey(it.day) && viewModel.expenseMoneyOfDay[it.day]!! > 0L)
                            Text(
                                text = "-${longToMoneyUnit(viewModel.expenseMoneyOfDay[it.day]!!)}",
                                fontSize = 10.sp,
                                color = Red,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        if ((viewModel.incomeMoneyOfDay.getOrDefault(it.day, 0L) - viewModel.expenseMoneyOfDay.getOrDefault(it.day, 0L)) != 0L)
                            Text(
                                text = longToMoneyUnit(
                                    (viewModel.incomeMoneyOfDay.getOrDefault(it.day, 0L) - viewModel.expenseMoneyOfDay.getOrDefault(
                                        it.day,
                                        0L
                                    ))
                                ),
                                fontSize = 10.sp,
                                color = Purple,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                    }
                    Text(
                        text = it.day.toString(),
                        style = MaterialTheme.typography.caption,
                        color = it.color,
                        modifier = Modifier.align(Alignment.End),
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun BothText(
    leftText: String,
    rightText: String,
    textColor: Color
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)) {
        Text(text = leftText, modifier = Modifier.align(Alignment.CenterStart), fontWeight = FontWeight.Bold)
        Text(text = rightText, Modifier.align(Alignment.CenterEnd), color = textColor, fontWeight = FontWeight.Bold)
    }
}

private fun getCalendar(date: Date): List<CalendarText> {
    var number = getDayOfWeekNumber(Date(date.year, date.month, 1))
    var count = if (number in 0..5) number + 1 else 0
    val calendar = mutableListOf<CalendarText>()
    val prevDate = decreaseDate(date)
    val prevLength = getEndOfMonth(prevDate)
    for (i in (count - 1) downTo 0) calendar.add(CalendarText(prevLength - i, Purple40))
    val curLength = getEndOfMonth(date)
    for (i in 1..curLength) calendar.add(CalendarText(i, Purple))
    number = getDayOfWeekNumber(Date(date.year, date.month, curLength))
    count = if (number in 0..5) 5 - number else 6
    for (i in 1..count) calendar.add(CalendarText(i, Purple40))
    return calendar
}

data class CalendarText(
    val day: Int,
    val color: Color
)

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun CalendarItemPreview() {
    CalendarScreen(accountBookViewModel = hiltViewModel())
}