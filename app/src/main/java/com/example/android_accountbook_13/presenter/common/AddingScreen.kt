package com.example.android_accountbook_13.presenter.common

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.presenter.component.AddingButton
import com.example.android_accountbook_13.presenter.setting.SettingViewModel
import com.example.android_accountbook_13.presenter.setting.component.SettingHeader
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.ui.theme.OffWhite
import com.example.android_accountbook_13.ui.theme.Purple

@Composable
fun AddingScreen(
    navController: NavHostController,
    title: String,
    id: Int?,
    type: Boolean,
    viewModel: SettingViewModel
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
    var color by rememberSaveable { mutableStateOf(if(title == "수입") incomeColors[0] else expenseColors[0]) }
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        topBar = {
            com.example.android_accountbook_13.presenter.component.TopAppBar(
                title = screenTitle,
                leftVectorResource = R.drawable.ic_back,
                onLeftClick = { navController.popBackStack() }
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

            if (title != "결제") {
                Divider(color = LightPurple, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
                SettingHeader(title = "색상")
                if (title == "지출") {
                    ColorPalette(expenseColors, selectedIndex) { selectedColor, index ->
                        color = selectedColor
                        selectedIndex = index
                    }
                } else {
                    ColorPalette(incomeColors, selectedIndex) { selectedColor, index ->
                        color = selectedColor
                        selectedIndex = index
                    }
                }
            }
            Divider(color = Purple, modifier = Modifier.padding(top = 8.dp))

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                AddingButton(
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
                                if (title == "수입") {
                                    Log.d("Test","수입 Insert")
                                    viewModel.insertCategory(Category(null, text, color, 0))
                                } else {
                                    Log.d("Test","지출 Insert")
                                    viewModel.insertCategory(Category(null, text, color, 1))
                                }
                            } else {
                                if (title == "수입") {
                                    viewModel.updateCategory(Category(id, text, color, 0))
                                } else {
                                    viewModel.updateCategory(Category(id, text, color, 1))
                                }
                            }
                        }
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ColorPalette(
    colors: List<String>,
    selectedIndex: Int,
    onClick: (String, Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        cells = GridCells.Fixed(10),
        verticalArrangement = Arrangement.Center
    ) {
        colors.forEachIndexed { index, color ->
            item {
                Box(modifier = Modifier.size(32.dp).padding(4.dp)) {
                    Surface(
                        modifier = Modifier
                            .clickable { onClick(color, index) }
                            .size(if (selectedIndex == index) 24.dp else 16.dp)
                            .align(Alignment.Center)
                            .animateContentSize(TweenSpec()),
                        color = Color(android.graphics.Color.parseColor(color))
                    ) {}
                }
            }
        }
    }
}

val incomeColors = listOf(
    "#9BD182", "#A3CB7A", "#B5CC7A",
    "#CCD67A", "#EAE07C", "#EDCF65",
    "#EBC374", "#E1AD60", "#E29C4D", "#E39145",
)
val expenseColors = listOf(
    "#4A6CC3", "#2E86C7", "#4CA1DE", "#48C2E9",
    "#6ED5EB", "#9FE7C8", "#94D3CC", "#4CB8B8",
    "#40B98D", "#2FA488", "#625EBA", "#817DCE",
    "#9B7DCE", "#B391EB", "#D092E2", "#F1B4EF",
    "#F4AEE1", "#F396B8", "#DC5D7B", "#CB588F",
)