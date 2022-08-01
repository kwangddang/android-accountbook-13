package com.example.android_accountbook_13.presenter.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_accountbook_13.ui.theme.White
import com.example.android_accountbook_13.ui.theme.Yellow

@Composable
fun Category(
    title: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .width(64.dp)
            .height(20.dp),
        color = backgroundColor,
        contentColor = White,
        shape = RoundedCornerShape(8.dp),
        ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Preview
@Composable
private fun CategoryPreview() {
    Category("문화/여가", Yellow)
}