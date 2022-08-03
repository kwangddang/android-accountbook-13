package com.example.android_accountbook_13.presenter.statistic

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.presenter.calendar.BothText
import com.example.android_accountbook_13.presenter.component.Category
import com.example.android_accountbook_13.presenter.component.TopAppBar
import com.example.android_accountbook_13.presenter.component.YearMonthDatePicker
import com.example.android_accountbook_13.presenter.history.HistoryViewModel
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.ui.theme.Purple
import com.example.android_accountbook_13.ui.theme.Red
import com.example.android_accountbook_13.utils.*
import com.example.android_accountbook_13.utils.Date
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.util.*

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun StatisticScreen(
    historyViewModel: HistoryViewModel
) {
    val date by historyViewModel.date
    var isDialog by rememberSaveable { mutableStateOf(false) }
    val totalMoney by historyViewModel.expenseMoney
    Scaffold(
        topBar = {
            TopAppBar(
                title = getYearMonthString(date),
                titleOnClick = { isDialog = true },
                leftVectorResource = R.drawable.ic_left,
                rightVectorResource = R.drawable.ic_right,
                onLeftClick = {
                    historyViewModel.decreaseDate()
                },
                onRightClick = {
                    historyViewModel.increaseDate()
                }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        if (isDialog) {
            Dialog(onDismissRequest = { isDialog = false }) {
                YearMonthDatePicker(onDismissRequest = { isDialog = false }) { year, month ->
                    historyViewModel.changeDate(year,month)
                    isDialog = false
                }
            }
        }
        val list = if(totalMoney > 0 ) historyViewModel.getPairList() else emptyList()

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                BothText(leftText = stringResource(id = R.string.total_payment_of_month), rightText = moneyConverter(totalMoney), textColor = Red)
                Divider(color = Purple, modifier = Modifier.padding(top = 8.dp))
            }
            item{
                AndroidView(factory = {
                    val pieChart = PieChart(it)
                    pieChart.setTouchEnabled(false)
                    pieChart.description.isEnabled = false
                    pieChart.legend.isEnabled = false
                    pieChart.setHoleColor(0)

                    val colorList = mutableListOf<Int>()
                    val pieEntryList = mutableListOf<PieEntry>().apply {
                        for(pair in list) {
                            this.add(
                                PieEntry(pair.first.toFloat(), pair.first / totalMoney.toFloat())
                            )
                            colorList.add(Color.parseColor(pair.second.color))
                        }
                    }
                    val dataSet = PieDataSet(pieEntryList,"expense")
                    dataSet.setDrawValues(false)
                    dataSet.colors = colorList
                    dataSet.sliceSpace = 1f
                    val pieData = PieData(dataSet)
                    pieChart.data = pieData
                    pieChart.animateY(1000, Easing.EaseInBack)
                    pieChart
                },
                    update = {
                        val colorList = mutableListOf<Int>()
                        val pieEntryList = mutableListOf<PieEntry>().apply {
                            for(pair in list) {
                                this.add(
                                    PieEntry(pair.first.toFloat(), pair.first / totalMoney.toFloat())
                                )
                                colorList.add(Color.parseColor(pair.second.color))
                            }
                        }
                        val dataSet = PieDataSet(pieEntryList,"expense")
                        dataSet.colors = colorList
                        dataSet.sliceSpace = 1f
                        dataSet.setDrawValues(false)
                        val pieData = PieData(dataSet)
                        it.data = pieData
                        it.animateY(1000, Easing.EaseInBack)
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp))
            }
            items(list) { pair ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)) {
                    Category(
                        title = pair.second.name,
                        backgroundColor = androidx.compose.ui.graphics.Color(Color.parseColor(pair.second.color)),
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    BothText(leftText = moneyConverter(pair.first), rightText = "${pair.first * 100 / totalMoney}%", textColor = Purple)
                }
                Divider(Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp), color = LightPurple)
            }
        }
    }
}