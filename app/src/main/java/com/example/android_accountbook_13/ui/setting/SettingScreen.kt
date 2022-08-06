package com.example.android_accountbook_13.ui.setting

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.ui.common.component.TopAppBar
import com.example.android_accountbook_13.ui.setting.component.SettingContent
import com.example.android_accountbook_13.ui.setting.component.SettingFooter
import com.example.android_accountbook_13.ui.setting.component.SettingHeader
import com.example.android_accountbook_13.utils.showToast

@Composable
fun SettingScreen(
    navController: NavHostController = rememberNavController(),
    settingViewModel: SettingViewModel
) {
    val context = LocalContext.current
    val methods by settingViewModel.methods.collectAsState()
    val incomeCategories by settingViewModel.incomeCategories.collectAsState()
    val expenseCategories by settingViewModel.expenseCategories.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(id = R.string.setting),
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        LazyColumn {
            item {
                SettingHeader(title = stringResource(id = R.string.payment_method))
            }

            items(methods) { method ->
                SettingContent(
                    title = method.name,
                    onClick = {
                        navController.navigate("addingSetting?title=결제&name=${method.name}&id=${method.id}&type=false")
                    }
                )
            }

            item {
                SettingFooter(
                    title = "${stringResource(id = R.string.payment_method)} ${stringResource(id = R.string.add_item)}",
                    onClick = {
                        navController.navigate("addingSetting?title=결제&id=-1&type=true")
                    }
                )
            }

            item {
                SettingHeader(title = stringResource(id = R.string.category_expense))
            }

            items(expenseCategories) { category ->
                SettingContent(
                    title = category.name,
                    category = category,
                    onClick = {
                        if(category.id == 1) showToast(context, "미분류 카테고리는 수정할 수 없습니다.")
                        else navController.navigate("addingSetting?title=지출&name=${category.name}&id=${category.id}&type=false")
                    }
                )
            }

            item {
                SettingFooter(
                    title = "${stringResource(id = R.string.category_expense)} ${stringResource(id = R.string.add_item)}",
                    onClick = {
                        navController.navigate("addingSetting?title=지출&id=-1&type=true")
                    }
                )
            }

            item {
                SettingHeader(title = stringResource(id = R.string.category_income))
            }

            items(incomeCategories) { category ->
                SettingContent(
                    title = category.name,
                    category = category,
                    onClick = {
                        if(category.id == 2) showToast(context, "미분류 카테고리는 수정할 수 없습니다.")
                        else navController.navigate("addingSetting?title=수입&name=${category.name}&id=${category.id}&type=false")
                    }
                )
            }

            item {
                SettingFooter(
                    title = "${stringResource(id = R.string.category_income)} ${stringResource(id = R.string.add_item)}",
                    onClick = {
                        navController.navigate("addingSetting?title=수입&id=-1&type=true")
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