package com.example.android_accountbook_13.presenter.calendar

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
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

@Composable
fun CalendarScreen(
    historyViewModel: HistoryViewModel
) {
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
            Calendar(date)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(date: Date) {
    val calendar = getCalendar(date)

    LazyVerticalGrid(cells = GridCells.Fixed(7)){
        calendar.forEach {
            item {
                Column(modifier = Modifier
                    .border(0.5.dp, LightPurple)
                    .size(72.dp)
                    .padding(4.dp)) {
                    Text(text = "5,400", style = MaterialTheme.typography.caption, color = Green6)
                    Text(text = "-5,400", style = MaterialTheme.typography.caption, color = Red)
                    Text(text = "5,400", style = MaterialTheme.typography.caption, color = Purple)
                    Text(text = it.text.toString(), style = MaterialTheme.typography.caption, color = it.color, modifier = Modifier.align(Alignment.End))
                }
            }
        }
    }
}

private fun getCalendar(date: Date): List<CalendarText> {
    var number = getDayOfWeekNumber(Date(date.year,date.month,1))
    var count = if(number in 0..5) number + 1 else 0
    val calendar = mutableListOf<CalendarText>()
    val prevDate = decreaseDate(date)
    val prevLength = getEndOfMonth(prevDate)
    for(i in (count - 1) downTo 0) calendar.add(CalendarText(prevLength - i, Purple40))
    val curLength = getEndOfMonth(date)
    for(i in 1 .. curLength) calendar.add(CalendarText(i, Purple))
    number = getDayOfWeekNumber(Date(date.year,date.month,curLength))
    count = if(number in 0..5) 5 - number else 6
    for(i in 1..count) calendar.add(CalendarText(i, Purple40))
    return calendar
}

data class CalendarText(
    val text: Int,
    val color: Color
)

@Preview
@Composable
fun CalendarItemPreview() {
    CalendarScreen(historyViewModel = hiltViewModel())
}