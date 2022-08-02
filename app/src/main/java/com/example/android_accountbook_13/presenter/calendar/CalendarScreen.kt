package com.example.android_accountbook_13.presenter.calendar

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.presenter.component.TopAppBar
import com.example.android_accountbook_13.presenter.component.YearMonthDatePicker
import com.example.android_accountbook_13.presenter.history.HistoryViewModel
import com.example.android_accountbook_13.ui.theme.*
import com.example.android_accountbook_13.utils.*

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CalendarScreen(
    historyViewModel: HistoryViewModel
) {
    historyViewModel.getAccountBookItems()
    historyViewModel.getCheckedItems(true,true)
    var date by historyViewModel.date
    var isDialog by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = getYearMonthString(date),
                titleOnClick = { isDialog = true },
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
        backgroundColor = MaterialTheme.colors.background
    ) {
        if (isDialog) {
            Dialog(onDismissRequest = { isDialog = false }) {
                YearMonthDatePicker(onDismissRequest = { isDialog = false }) { year, month ->
                    date = Date(year, month, 1)
                    isDialog = false
                }
            }
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Calendar(date, historyViewModel)
            BothText("수입", moneyConverter(historyViewModel.incomeMoney.value), Green6)
            Divider(modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp), color = LightPurple)
            BothText("지출", "-"  + moneyConverter(historyViewModel.expenseMoney.value), Red)
            Divider(modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp), color = LightPurple)
            BothText("총합", moneyConverter(historyViewModel.incomeMoney.value - historyViewModel.expenseMoney.value), Purple)
            Divider(modifier = Modifier.padding(top = 8.dp), color = Purple)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(
    date: Date,
    viewModel: HistoryViewModel
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
                                text = moneyConverter(viewModel.incomeMoneyOfDay[it.day]!!),
                                style = MaterialTheme.typography.caption,
                                color = Green6,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        if (viewModel.expenseMoneyOfDay.containsKey(it.day) && viewModel.expenseMoneyOfDay[it.day]!! > 0L)
                            Text(
                                text = "-${moneyConverter(viewModel.expenseMoneyOfDay[it.day]!!)}",
                                style = MaterialTheme.typography.caption,
                                color = Red,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        if ((viewModel.incomeMoneyOfDay.getOrDefault(it.day, 0L) - viewModel.expenseMoneyOfDay.getOrDefault(it.day, 0L)) != 0L)
                            Text(
                                text = moneyConverter(
                                    (viewModel.incomeMoneyOfDay.getOrDefault(it.day, 0L) - viewModel.expenseMoneyOfDay.getOrDefault(
                                        it.day,
                                        0L
                                    ))
                                ),
                                style = MaterialTheme.typography.caption,
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
private fun BothText(
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
    CalendarScreen(historyViewModel = hiltViewModel())
}