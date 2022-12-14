package com.example.android_accountbook_13.ui.setting.component

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.ui.common.component.Category
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
        .clickable(onClick = onClick)
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
                Category(
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
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, top = 24.dp)) {
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
        .clickable(onClick = onClick)
        .padding(top = 8.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)) {
            Text(
                text = title,
                fontSize = 16.sp,
                color = Purple,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus), contentDescription = stringResource(id = R.string.add),
                modifier = Modifier.align(Alignment.CenterEnd), tint = Purple
            )
        }
        Divider(color = Purple, modifier = Modifier.padding(top = 8.dp))
    }
}

@Preview
@Composable
private fun SettingHeaderPreview() {
    SettingHeader("????????????")
}

@Preview
@Composable
private fun SettingContentPreview() {
    SettingContent(title = "??????", category = Category(null, "??????", "#372912", 0)) {

    }
}

@Preview
@Composable
private fun SettingFooterPreview() {
    SettingFooter(title = "???????????? ????????????") {

    }
}