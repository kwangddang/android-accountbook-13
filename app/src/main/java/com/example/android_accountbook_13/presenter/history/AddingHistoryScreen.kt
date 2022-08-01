package com.example.android_accountbook_13.presenter.history

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.presenter.component.AddingButton
import com.example.android_accountbook_13.presenter.component.SwitchButton
import com.example.android_accountbook_13.presenter.component.TopAppBar
import com.example.android_accountbook_13.presenter.component.YearMonthDayDatePicker
import com.example.android_accountbook_13.presenter.setting.SettingViewModel
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.ui.theme.OffWhite
import com.example.android_accountbook_13.ui.theme.Purple
import com.example.android_accountbook_13.utils.Date
import com.example.android_accountbook_13.utils.getCurrentDate
import com.example.android_accountbook_13.utils.getYearMonthDayString

@Composable
fun AddingHistoryScreen(
    navHostController: NavHostController,
    method: Int,
    id: Int?,
    historyViewModel: HistoryViewModel,
    settingViewModel: SettingViewModel
) {
    var price by rememberSaveable { mutableStateOf("") }
    var content by rememberSaveable { mutableStateOf("") }

    var checkedMethod by remember { mutableStateOf(Method(null, "")) }
    var methodExpanded by rememberSaveable { mutableStateOf(false) }

    var checkedCategory by remember { mutableStateOf(Category(null, "", "", -1)) }
    var categoryExpanded by rememberSaveable { mutableStateOf(false) }

    var isDialog by rememberSaveable { mutableStateOf(false) }

    var date by remember { mutableStateOf(getCurrentDate()) }

    var incomeChecked by rememberSaveable { mutableStateOf(method == 0) }
    var expenseChecked by rememberSaveable { mutableStateOf(method == 1) }

    val methods by settingViewModel.methods.collectAsState()
    val incomeCategories by settingViewModel.incomeCategories.collectAsState()
    val expenseCategories by settingViewModel.expenseCategories.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = "내역 ${if (id == -1) "등록" else "수정"}",
                leftVectorResource = R.drawable.ic_back,
                onLeftClick = { navHostController.popBackStack() }
            )
        },
        backgroundColor = OffWhite,
    ) {
        if (isDialog) {
            Dialog(onDismissRequest = { isDialog = false }) {
                YearMonthDayDatePicker(onDismissRequest = { isDialog = false }) { year, month, day ->
                    date = Date(year, month, day)
                    isDialog = false
                }
            }
        }
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            SwitchButton(
                incomeChecked = incomeChecked,
                expenseChecked = expenseChecked,
                onIncomeClick = {
                    incomeChecked = true
                    expenseChecked = false
                },
                onExpenseClick = {
                    incomeChecked = false
                    expenseChecked = true
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            AddingHistoryItem(title = "일자") {

                Text(text = getYearMonthDayString(date),
                    modifier = Modifier
                        .clickable { isDialog = true }
                        .width(280.dp)
                        .height(56.dp)
                        .padding(start = 16.dp, top = 16.dp),
                    fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Purple)
            }

            AddingHistoryItem(title = "금액") {
                AddingHistoryTextField(
                    text = price,
                    onValueChange = { textValue ->
                        if ((textValue[textValue.lastIndex] in '0'..'9')) price = textValue
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
            }

            AddingHistoryItem(title = "결제 수단") {
                Box() {
                    AddingHistoryTextField(checkedMethod.name, "선택하세요", {}, true)
                    AddingHistorySpinner(
                        Modifier.Companion.align(Alignment.CenterEnd),
                        methodExpanded,
                        methods,
                        { methodExpanded = !methodExpanded },
                        { method ->
                            checkedMethod = method as Method
                            methodExpanded = false
                        },
                        { methodExpanded = false }
                    )
                }
            }

            AddingHistoryItem(title = "분류") {
                Box() {
                    AddingHistoryTextField(checkedCategory.name, "선택하세요", {}, true)
                    AddingHistorySpinner(
                        Modifier.Companion.align(Alignment.CenterEnd),
                        categoryExpanded,
                        if (incomeChecked) incomeCategories else expenseCategories,
                        { categoryExpanded = !categoryExpanded },
                        { category ->
                            checkedCategory = category as Category
                            categoryExpanded = false
                        },
                        { categoryExpanded = false }
                    )
                }
            }

            AddingHistoryItem(title = "내용") {
                AddingHistoryTextField(text = content, onValueChange = { textValue -> content = textValue })
            }
            Box(modifier = Modifier.fillMaxSize()) {
                AddingButton(
                    enabled = (price.isNotBlank() && price.isNotEmpty() && (checkedMethod.name.isNotEmpty() && checkedMethod.name.isNotBlank())),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 48.dp)
                        .height(56.dp)
                ) {
                    if(id == -1)
                    {
                        historyViewModel.insertHistory(
                            History(
                                null,
                                checkedCategory.id ?: 3,
                                checkedMethod.id!!, content,
                                if (incomeChecked) 1 else 0,
                                price.toLong(),
                                date.year, date.month, date.day
                            )
                        )
                    }
                    else {
                        historyViewModel.updateHistory(
                            History(
                                id,
                                checkedCategory.id ?: 3,
                                checkedMethod.id!!, content,
                                if (incomeChecked) 1 else 0,
                                price.toLong(),
                                date.year, date.month, date.day
                            )
                        )
                    }
                    navHostController.popBackStack()
                }
            }
        }
    }
}

@Composable
private fun AddingHistorySpinner(
    modifier: Modifier = Modifier,
    expended: Boolean,
    items: List<*>,
    onIconClick: () -> Unit,
    onItemClick: (Any) -> Unit,
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
                    .height(156.dp),
                offset = DpOffset(0.dp,16.dp)
            ) {
                items.forEach { item ->
                    if (item is Method) {
                        DropdownMenuItem(onClick = {
                            onItemClick(item)
                        }) {
                            Text(text = item.name)
                        }
                    } else if (item is Category) {
                        DropdownMenuItem(onClick = { onItemClick(item) }) {
                            Text(text = item.name)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AddingHistoryItem(
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
private fun AddingHistoryTextField(
    text: String,
    placeHolder: String = "입력하세요",
    onValueChange: (String) -> Unit,
    readOnly: Boolean = false,
    modifier: Modifier? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
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
        modifier = modifier ?: Modifier,
        keyboardOptions = keyboardOptions
    )
}