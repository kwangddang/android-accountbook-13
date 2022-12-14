package com.example.android_accountbook_13.ui.accountbook.history

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.ui.accountbook.AccountBookViewModel
import com.example.android_accountbook_13.ui.common.component.AddingButton
import com.example.android_accountbook_13.ui.common.component.SwitchButton
import com.example.android_accountbook_13.ui.common.component.TopAppBar
import com.example.android_accountbook_13.ui.common.component.YearMonthDayDatePicker
import com.example.android_accountbook_13.ui.setting.SettingViewModel
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.ui.theme.OffWhite
import com.example.android_accountbook_13.ui.theme.Purple
import com.example.android_accountbook_13.utils.*

@Composable
fun AddingHistoryScreen(
    navHostController: NavHostController,
    method: Int,
    accountBookViewModel: AccountBookViewModel,
    settingViewModel: SettingViewModel
) {
    val context = LocalContext.current
    BackHandler() {
        accountBookViewModel.navItem = null
        navHostController.popBackStack()
    }
    val navItem = accountBookViewModel.navItem
    var date by rememberSaveable {
        mutableStateOf(
            if (navItem == null) getCurrentDate()
            else Date(navItem.history.year, navItem.history.month, navItem.history.day)
        )
    }
    var price by rememberSaveable { mutableStateOf(navItem?.history?.money?.toString() ?: "") }
    var checkedMethod by rememberSaveable { mutableStateOf(navItem?.method ?: Method(null, "")) }
    var checkedCategory by rememberSaveable { mutableStateOf(navItem?.category ?: Category(null, "", "", -1)) }
    var content by rememberSaveable { mutableStateOf(navItem?.history?.name ?: "") }

    var methodExpanded by rememberSaveable { mutableStateOf(false) }
    var categoryExpanded by rememberSaveable { mutableStateOf(false) }
    var isDialog by rememberSaveable { mutableStateOf(false) }

    var incomeChecked by rememberSaveable { mutableStateOf(method == 0) }
    var expenseChecked by rememberSaveable { mutableStateOf(method == 1) }

    val methods by settingViewModel.methods.collectAsState()
    val incomeCategories by settingViewModel.incomeCategories.collectAsState()
    val expenseCategories by settingViewModel.expenseCategories.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = "${stringResource(id = R.string.history)} ${if (navItem == null) stringResource(id = R.string.add) else stringResource(id = R.string.edit)}",
                leftVectorResource = R.drawable.ic_back,
                onLeftClick = {
                    accountBookViewModel.navItem = null
                    navHostController.popBackStack()
                }
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
                    checkedCategory = Category(null,"","",0)
                },
                onExpenseClick = {
                    incomeChecked = false
                    expenseChecked = true
                    checkedCategory = Category(null,"","",1)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            AddingHistoryItem(title = stringResource(id = R.string.date)) {

                Text(text = getYearMonthDayString(date),
                    modifier = Modifier
                        .clickable { isDialog = true }
                        .width(280.dp)
                        .height(56.dp)
                        .padding(start = 16.dp, top = 16.dp),
                    fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Purple)
            }

            AddingHistoryItem(title = stringResource(id = R.string.price)) {
                AddingHistoryTextField(
                    text = price,
                    onValueChange = { textValue ->
                        if (textValue.isEmpty()) price = ""
                        else if ((textValue[textValue.lastIndex] in '0'..'9' && textValue[0] != '0' && textValue.length < 11)) price = textValue
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    visualTransformation = { priceVisualTransformation(price) }
                )
            }

            AddingHistoryItem(title = stringResource(id = R.string.payment_method)) {
                Box() {
                    AddingHistoryTextField(checkedMethod.name, stringResource(id = R.string.placeholder), {}, true)
                    AddingHistorySpinner(
                        Modifier.align(Alignment.CenterEnd),
                        methodExpanded,
                        methods,
                        { methodExpanded = !methodExpanded },
                        { method ->
                            checkedMethod = method as Method
                            methodExpanded = false
                        },
                        { navHostController.navigate("addingSetting?title=??????&id=-1&type=true") },
                        { methodExpanded = false }
                    )
                }
            }

            AddingHistoryItem(title = "??????") {
                Box() {
                    AddingHistoryTextField(checkedCategory.name, stringResource(id = R.string.placeholder), {}, true)
                    AddingHistorySpinner(
                        Modifier.align(Alignment.CenterEnd),
                        categoryExpanded,
                        if (incomeChecked) incomeCategories else expenseCategories,
                        { categoryExpanded = !categoryExpanded },
                        { category ->
                            checkedCategory = category as Category
                            categoryExpanded = false
                        },
                        {
                            navHostController.navigate(
                                if (incomeChecked) "addingSetting?title=??????&id=-1&type=true"
                                else "addingSetting?title=??????&id=-1&type=true"
                            )
                        },
                        { categoryExpanded = false }
                    )
                }
            }

            AddingHistoryItem(title = stringResource(id = R.string.content)) {
                AddingHistoryTextField(text = content, onValueChange = { textValue -> content = textValue })
            }
            Box(modifier = Modifier.fillMaxSize()) {
                AddingButton(
                    enabled = (price.isNotBlank() && price.isNotEmpty() && (checkedMethod.name.isNotEmpty() && checkedMethod.name.isNotBlank())),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 48.dp)
                        .height(56.dp),
                    title = if (navItem == null) stringResource(id = R.string.btn_add) else stringResource(id = R.string.btn_edit)
                ) {
                    if (navItem == null) {
                        accountBookViewModel.insertHistory(
                            History(
                                null,
                                if (incomeChecked) checkedCategory.id ?: 2 else checkedCategory.id ?: 1,
                                checkedMethod.id!!, content,
                                if (incomeChecked) 0 else 1,
                                price.toLong(),
                                date.year, date.month, date.day
                            ), { message ->
                                showToast(context, message)
                            }, {
                                accountBookViewModel.navItem = null
                                accountBookViewModel.getAccountBookItems()
                                navHostController.popBackStack()
                            }
                        )
                    } else {
                        accountBookViewModel.updateHistory(
                            History(
                                navItem.history.id,
                                checkedCategory.id ?: 3,
                                checkedMethod.id!!, content,
                                if (incomeChecked) 0 else 1,
                                price.toLong(),
                                date.year, date.month, date.day
                            ), { message ->
                                showToast(context, message)
                            }, {
                                accountBookViewModel.navItem = null
                                accountBookViewModel.getAccountBookItems()
                                navHostController.popBackStack()
                            }
                        )
                    }
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
    onAddClick: () -> Unit,
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
                offset = DpOffset(0.dp, 16.dp)
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
                DropdownMenuItem(onClick = onAddClick) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.add_item), modifier = Modifier.align(Alignment.CenterStart))
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                            contentDescription = stringResource(id = R.string.add_item),
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
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
    placeHolder: String = "???????????????",
    onValueChange: (String) -> Unit,
    readOnly: Boolean = false,
    modifier: Modifier? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Purple,
            backgroundColor = OffWhite,
            focusedIndicatorColor = OffWhite,
            unfocusedIndicatorColor = OffWhite,
            cursorColor = Purple
        ),
        placeholder = { Text(text = placeHolder, color = LightPurple, fontWeight = FontWeight.Bold) },
        readOnly = readOnly,
        modifier = modifier ?: Modifier,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
    )
}