package com.example.android_accountbook_13.presenter.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.presenter.component.AccountBookTopAppBar
import com.example.android_accountbook_13.presenter.setting.component.SettingContent
import com.example.android_accountbook_13.presenter.setting.component.SettingFooter
import com.example.android_accountbook_13.presenter.setting.component.SettingHeader
import com.example.android_accountbook_13.ui.theme.Yellow

@Composable
fun SettingScreen(
    viewModel: SettingViewModel = hiltViewModel()
) {
    viewModel.run {
        getAllMethod()
        getIncomeCategory()
        getExpenseCategory()
    }
    val methods by viewModel.methods.collectAsState()
    val incomeCategories by viewModel.incomeCategories.collectAsState()
    val expenseCategories by viewModel.expenseCategories.collectAsState()

    Scaffold(
        topBar = {
            AccountBookTopAppBar(
                title = "설정",
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        LazyColumn {
            item {
                SettingHeader(title = "결제수단")
            }

            items(methods) { method ->
                SettingContent(
                    title = method.name,
                    onClick = {}
                )
            }

            item {
                SettingFooter(
                    title = "결제수단 추가하기",
                    onClick = {}
                )
            }

            item {
                SettingHeader(title = "지출 카테고리")
            }

            items(expenseCategories) { category ->
                SettingContent(
                    title = category.name,
                    category = category,
                    onClick = {}
                )
            }

            item {
                SettingFooter(
                    title = "지출 카테고리 추가하기",
                    onClick = {}
                )
            }

            item {
                SettingHeader(title = "수입 카테고리")
            }

            items(incomeCategories) { category ->
                SettingContent(
                    title = category.name,
                    category = category,
                    onClick = {}
                )
            }

            item {
                SettingFooter(
                    title = "수입 카테고리 추가하기",
                    onClick = {}
                )
            }

            item {
                Spacer(modifier = Modifier.height(48.dp).fillMaxWidth())
            }
        }
    }
}