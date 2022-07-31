package com.example.android_accountbook_13.presenter.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_accountbook_13.ui.theme.LightPurple

@Composable
fun AccountBookHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
        Text(text = title, fontSize = 18.sp, color = LightPurple)
        Divider(color = LightPurple, modifier = Modifier.padding(top = 8.dp))
    }
}