package com.example.android_accountbook_13.presenter.history

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.presenter.component.AccountBookAddingButton
import com.example.android_accountbook_13.presenter.component.AccountBookSwitchButton
import com.example.android_accountbook_13.presenter.component.AccountBookTopAppBar
import com.example.android_accountbook_13.presenter.component.YearMonthDatePicker
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.ui.theme.OffWhite
import com.example.android_accountbook_13.ui.theme.Purple
import com.example.android_accountbook_13.utils.Date
import com.example.android_accountbook_13.utils.getCurrentDate
import com.example.android_accountbook_13.utils.getYearMonthDayString

@Composable
fun HistoryAdditionScreen(
    navHostController: NavHostController,
    method: Int,
    id: Int?
) {
    var price by rememberSaveable { mutableStateOf("") }
    var content by rememberSaveable { mutableStateOf("") }

    var methodTitle by rememberSaveable { mutableStateOf("") }
    var methodExpanded by rememberSaveable { mutableStateOf(false) }

    var categoryTitle by rememberSaveable { mutableStateOf("") }
    var categoryExpanded by rememberSaveable { mutableStateOf(false) }

    var isDialog by rememberSaveable { mutableStateOf(false) }

    var date by remember { mutableStateOf(getCurrentDate()) }
    //val methods by viewModel.methods.collectAsState()

    Scaffold(
        topBar = {
            AccountBookTopAppBar(
                title = "내역 ${if (id == -1) "등록" else "수정"}",
                leftVectorResource = R.drawable.ic_back
            )
        },
        backgroundColor = OffWhite,
    ) {
        if (isDialog) {
            Dialog(onDismissRequest = { isDialog = false }) {
                YearMonthDatePicker(onDismissRequest = { isDialog = false }) { year, month, day ->
                    date = Date(year,month,day)
                    isDialog = false
                }
            }
        }
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            AccountBookSwitchButton(incomeChecked = true, expenseChecked = false)
            Spacer(modifier = Modifier.height(8.dp))

            HistoryAdditionItem(title = "일자") {

                Text(text = getYearMonthDayString(date),
                modifier = Modifier
                    .clickable { isDialog = true }
                    .width(280.dp)
                    .height(56.dp)
                    .padding(start = 16.dp, top = 16.dp),
                fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Purple)
            }

            HistoryAdditionItem(title = "금액") {
                HistoryAdditionTextField(text = price, onValueChange = { textValue -> price = textValue })
            }

            HistoryAdditionItem(title = "결제 수단") {
                Box() {
                    HistoryAdditionTextField(methodTitle, "선택하세요", {}, true)
                    HistoryAdditionSpinner(
                        Modifier.Companion.align(Alignment.CenterEnd),
                        methodExpanded,
                        { methodExpanded = !methodExpanded },
                        { methodName ->
                            methodTitle = methodName
                            methodExpanded = false
                        },
                        { methodExpanded = false }
                    )
                }
            }

            HistoryAdditionItem(title = "분류") {
                Box() {
                    HistoryAdditionTextField(categoryTitle, "선택하세요", {}, true)
                    HistoryAdditionSpinner(
                        Modifier.Companion.align(Alignment.CenterEnd),
                        categoryExpanded,
                        { categoryExpanded = !categoryExpanded },
                        { categoryName ->
                            categoryTitle = categoryName
                            categoryExpanded = false
                        },
                        { categoryExpanded = false }
                    )
                }
            }

            HistoryAdditionItem(title = "내용") {
                HistoryAdditionTextField(text = content, onValueChange = { textValue -> content = textValue })
            }
            Box(modifier = Modifier.fillMaxSize()) {
                AccountBookAddingButton(
                    enabled = true,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 48.dp)
                        .height(56.dp)
                ) {

                }
            }
        }
    }
}

@Composable
private fun HistoryAdditionSpinner(
    modifier: Modifier = Modifier,
    expended: Boolean,
    onIconClick: () -> Unit,
    onItemClick: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    IconButton(onClick = onIconClick, modifier = modifier) {
        Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_down), contentDescription = "spinner")
        MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(12.dp))) {
            DropdownMenu(
                expanded = expended,
                onDismissRequest = onDismissRequest,
                modifier = Modifier
                    .border(2.dp, Purple, shape = RoundedCornerShape(12.dp))
                    .width(256.dp)
            ) {
                test.forEach { item ->
                    DropdownMenuItem(onClick = { onItemClick(item) }) {
                        Text(text = item)
                    }
                }
            }
        }
    }
}

val test = listOf("a", "b", "c")

@Composable
private fun HistoryAdditionItem(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title, fontSize = 16.sp, color = Purple, modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
            )
            content()
        }
        Divider(color = LightPurple)
    }
}

@Composable
private fun HistoryAdditionTextField(
    text: String,
    placeHolder: String = "입력하세요",
    onValueChange: (String) -> Unit,
    readOnly: Boolean = false,
    modifier: Modifier? = null
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Purple,
            backgroundColor = OffWhite,
            focusedIndicatorColor = OffWhite,
            unfocusedIndicatorColor = OffWhite
        ),
        placeholder = { Text(text = placeHolder, color = LightPurple, fontWeight = FontWeight.Bold) },
        readOnly = readOnly,
        modifier = modifier ?: Modifier
    )
}

@Composable
@Preview(showBackground = true)
fun HistoryAdditionScreenPreview() {
    HistoryAdditionScreen(rememberNavController(),0,-1)
}