package com.example.android_accountbook_13.presenter.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.android_accountbook_13.ui.theme.White
import com.example.android_accountbook_13.ui.theme.Yellow
import com.example.android_accountbook_13.utils.Date
import com.example.android_accountbook_13.utils.getCurrentDate

@Composable
fun YearMonthDatePicker(
    onDismissRequest: () -> Unit,
    onClick: () -> Unit
) {
    val date = getCurrentDate()
    var year by remember { mutableStateOf(date.year) }
    var month by remember { mutableStateOf(date.month) }
    var day by remember { mutableStateOf(date.day) }
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = White
        ) {
            Column(Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    DatePicker(num = year, startNum = 1997, lastNum = date.year, onValueChange = { it -> year = it })
                    Text(text = "년", modifier = Modifier.padding(4.dp))
                    DatePicker(num = month, startNum = 1, lastNum = 12, onValueChange = { it -> month = it })
                    Text(text = "월", modifier = Modifier.padding(4.dp))
                    DatePicker(num = day, startNum = 1, lastNum = 31, onValueChange = { it -> day = it })
                    Text(text = "일", modifier = Modifier.padding(4.dp))
                }
                AccountBookAddingButton(enabled = true, modifier = Modifier.fillMaxWidth()) {
                    onClick()
                }
            }
        }
    }
}

@Composable
private fun DatePicker(
    num: Int,
    startNum: Int,
    lastNum: Int,
    onValueChange: (Int) -> Unit
) {
    NumberPicker(
        modifier = Modifier.width(80.dp),
        value = num,
        onValueChange = onValueChange,
        dividersColor = Yellow,
        range = startNum..lastNum,
        textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
    )
}

@Preview(showBackground = true)
@Composable
fun YearMonthDatePickerPreview() {
    YearMonthDatePicker({}) {

    }
}