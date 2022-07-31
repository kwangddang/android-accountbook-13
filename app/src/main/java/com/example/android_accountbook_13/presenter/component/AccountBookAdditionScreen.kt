package com.example.android_accountbook_13.presenter.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.presenter.setting.SettingViewModel
import com.example.android_accountbook_13.presenter.setting.component.SettingHeader
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.ui.theme.OffWhite
import com.example.android_accountbook_13.ui.theme.Purple

@Composable
fun AccountBookAdditionScreen(
    navController: NavHostController,
    title: String,
    id: Int?,
    type: Boolean,
    viewModel: SettingViewModel = hiltViewModel()
) {
    var screenTitle: String = ""
    screenTitle = if (type) {
        if (title == "결제") {
            "결제 수단 추가하기"
        } else {
            "${title} 카테고리 추가"
        }
    } else {
        if (title == "결제") {
            "결제 수단 수정하기"
        } else {
            "${title} 카테고리 수정"
        }
    }
    var text by rememberSaveable { mutableStateOf("") }
    var color by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            AccountBookTopAppBar(
                title = screenTitle,
                leftVectorResource = R.drawable.ic_back,
                onLeftClick = {navController.popBackStack()}
            )
        },
        backgroundColor = OffWhite
    ) {
        Column(modifier = Modifier.padding(top = 16.dp)) {
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "이름",
                    modifier = Modifier.weight(1f),
                    fontSize = 18.sp,
                    color = Purple,
                    textAlign = TextAlign.Start
                )
                TextField(
                    value = text,
                    onValueChange = { textValue -> text = textValue },
                    textStyle = TextStyle(fontSize = 18.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = LightPurple,
                        backgroundColor = OffWhite,
                        focusedIndicatorColor = OffWhite,
                        unfocusedIndicatorColor = OffWhite
                    ),
                    placeholder = { Text(text = "입력하세요") }
                )
            }
            Divider(color = LightPurple, modifier = Modifier.padding(start = 16.dp, end = 16.dp))

            if (title != "결제") {
                SettingHeader(title = "색상")
                if (title == "지출") {
                    ColorPalette(expenseColors1, { selectedColor -> color = selectedColor })
                    ColorPalette(expenseColors2, { selectedColor -> color = selectedColor })
                } else {
                    ColorPalette(incomeColors, { selectedColor -> color = selectedColor })
                }
            }
            Divider(color = Purple, modifier = Modifier.padding(top = 8.dp))

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                AccountBookAddingButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 48.dp)
                        .height(56.dp),
                    title = if (type) "등록하기" else "수정하기",
                    enabled = text.isNotBlank() && text.isNotEmpty(),
                    onClick = {
                        if (title == "결제") {
                            if (type) {
                                viewModel.insertMethod(Method(null, text))
                            } else {
                                viewModel.updateMethod(Method(id, text))
                            }
                        } else {
                            if (type) {
                                if(title == "수입"){
                                    viewModel.insertCategory(Category(null, text, color, 0))
                                } else {
                                    viewModel.insertCategory(Category(null, text, color, 1))
                                }
                            } else {
                                Log.d("Test", id.toString())
                                if(title == "수입"){
                                    viewModel.updateCategory(Category(id, text, color, 0))
                                } else {
                                    viewModel.updateCategory(Category(id, text, color, 1))
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun ColorPalette(
    colors: List<String>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(13.dp),
        contentPadding = PaddingValues(16.dp)

    ) {
        items(colors) { color ->
            Surface(
                modifier = Modifier
                    .clickable {
                        onClick(color)
                    }
                    .size(24.dp),
                color = Color(android.graphics.Color.parseColor(color))
            ) {

            }
        }
    }
}


val incomeColors = listOf(
    "#9BD182", "#A3CB7A", "#B5CC7A",
    "#CCD67A", "#EAE07C", "#EDCF65",
    "#EBC374", "#E1AD60", "#E29C4D", "#E39145",
)
val expenseColors1 = listOf(
    "#4A6CC3", "#2E86C7", "#4CA1DE", "#48C2E9",
    "#6ED5EB", "#9FE7C8", "#94D3CC", "#4CB8B8",
    "#40B98D", "#2FA488",
)
val expenseColors2 = listOf(
    "#625EBA", "#817DCE",
    "#9B7DCE", "#B391EB", "#D092E2", "#F1B4EF",
    "#F4AEE1", "#F396B8", "#DC5D7B", "#CB588F",
)