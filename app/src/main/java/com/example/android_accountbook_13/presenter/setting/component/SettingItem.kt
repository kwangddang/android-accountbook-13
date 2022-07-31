package com.example.android_accountbook_13.presenter.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.presenter.component.AccountBookCategory
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.ui.theme.Purple


@Composable
fun SettingContent(
    title: String,
    category: Category? = null,
    onClick: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick }
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Purple,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            if (category != null) {
                AccountBookCategory(
                    title = category.name,
                    backgroundColor = Color(android.graphics.Color.parseColor(category.color)),
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
        Divider(color = LightPurple, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun SettingHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
        Text(text = title, fontSize = 18.sp, color = LightPurple)
        Divider(color = LightPurple, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun SettingFooter(
    title: String,
    onClick: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick }
        .padding(top = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)) {
            Text(
                text = title,
                fontSize = 16.sp,
                color = Purple,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus), contentDescription = "추가",
                modifier = Modifier.align(Alignment.CenterEnd), tint = Purple
            )
        }
        Divider(color = Purple, modifier = Modifier.padding(top = 8.dp))
    }
}

@Preview
@Composable
private fun SettingHeaderPreview() {
    SettingHeader("결제수단")
}

@Preview
@Composable
private fun SettingContentPreview() {
    SettingContent(title = "교통", category = Category(null, "교통", "#372912", 0)) {

    }
}

@Preview
@Composable
private fun SettingFooterPreview() {
    SettingFooter(title = "결제수단 추가하기") {

    }
}