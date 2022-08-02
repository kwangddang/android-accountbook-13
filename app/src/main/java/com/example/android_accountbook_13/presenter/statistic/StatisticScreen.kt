package com.example.android_accountbook_13.presenter.statistic

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.presenter.calendar.BothText
import com.example.android_accountbook_13.presenter.component.TopAppBar
import com.example.android_accountbook_13.presenter.component.YearMonthDatePicker
import com.example.android_accountbook_13.presenter.history.HistoryViewModel
import com.example.android_accountbook_13.ui.theme.Purple
import com.example.android_accountbook_13.ui.theme.Red
import com.example.android_accountbook_13.utils.*

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun StatisticScreen(
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
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                BothText(leftText = "이번 달 총 지출 금액", rightText = moneyConverter(historyViewModel.expenseMoney.value), textColor = Red)
                Divider(color = Purple, modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}