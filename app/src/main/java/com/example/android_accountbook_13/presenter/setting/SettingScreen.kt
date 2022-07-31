package com.example.android_accountbook_13.presenter.setting

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_accountbook_13.presenter.component.AccountBookTopAppBar
import com.example.android_accountbook_13.presenter.setting.component.SettingContent
import com.example.android_accountbook_13.presenter.setting.component.SettingFooter
import com.example.android_accountbook_13.presenter.setting.component.SettingHeader

@Composable
fun SettingScreen(
    navController: NavHostController = rememberNavController(),
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
                    onClick = {
                        navController.navigate("addition/결제,${method.id},false")
                    }
                )
            }

            item {
                SettingFooter(
                    title = "결제수단 추가하기",
                    onClick = {
                        navController.navigate("addition/결제,-1,true")
                    }
                )
            }

            item {
                SettingHeader(title = "지출 카테고리")
            }

            items(expenseCategories) { category ->
                SettingContent(
                    title = category.name,
                    category = category,
                    onClick = {
                        navController.navigate("addition/지출,${category.id},false")
                    }
                )
            }

            item {
                SettingFooter(
                    title = "지출 카테고리 추가하기",
                    onClick = {
                        navController.navigate("addition/지출,-1,true")
                    }
                )
            }

            item {
                SettingHeader(title = "수입 카테고리")
            }

            items(incomeCategories) { category ->
                SettingContent(
                    title = category.name,
                    category = category,
                    onClick = {
                        navController.navigate("addition/수입,${category.id},false")
                    }
                )
            }

            item {
                SettingFooter(
                    title = "수입 카테고리 추가하기",
                    onClick = {
                        navController.navigate("addition/수입,-1,true")
                    }
                )
            }

            item {
                Spacer(modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth())
            }
        }
    }
}