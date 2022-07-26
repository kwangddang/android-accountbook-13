package com.example.android_accountbook_13.presenter.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.ui.theme.MyTheme
import com.example.android_accountbook_13.ui.theme.White
import com.example.android_accountbook_13.ui.theme.White50

@Composable
fun BottomAppBars(
    onSelected: Int = 0,
    vectorResources: List<Int> = listOf(
        R.drawable.ic_history,
        R.drawable.ic_calendar,
        R.drawable.ic_statistic,
        R.drawable.ic_setting,
    ),
    stringResources: List<Int> = listOf(
        R.string.content_history,
        R.string.content_calendar,
        R.string.content_statistic,
        R.string.content_setting,
    ),
    onClick: () -> Unit = {}
) {
    MyTheme() {
        BottomAppBar(
            backgroundColor = MaterialTheme.colors.secondary,
            elevation = 1.dp,
        ) {
            for(i in 0..3) {
                val color = if(onSelected == i) White else White50
                ItemBottomAppBars(
                    stringResources[i],
                    vectorResources[i],
                    Modifier.weight(1f),
                    color,
                    onClick
                )
            }
        }
    }
}

@Composable
fun ItemBottomAppBars(
    stringResource: Int,
    vectorResource: Int,
    modifier: Modifier,
    color: Color,
    onClick: () -> Unit
) {
    Box(contentAlignment = Alignment.TopCenter, modifier = modifier.fillMaxHeight()) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = ImageVector.vectorResource(id = vectorResource),
                contentDescription = stringResource(id = stringResource),
                tint = color
            )
        }
        Text(
            text = stringResource(id = stringResource),
            modifier = Modifier.align(Alignment.BottomCenter),
            style = MaterialTheme.typography.caption,
            color = color
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BottomAppBarsPreview() {
    BottomAppBars()
}
