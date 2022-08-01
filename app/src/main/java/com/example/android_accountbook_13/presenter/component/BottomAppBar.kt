package com.example.android_accountbook_13.presenter.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.android_accountbook_13.presenter.AccountBookDestination
import com.example.android_accountbook_13.presenter.Calendar
import com.example.android_accountbook_13.presenter.History
import com.example.android_accountbook_13.presenter.bottomTabScreens
import com.example.android_accountbook_13.ui.theme.AccountBookTheme
import com.example.android_accountbook_13.ui.theme.Purple
import com.example.android_accountbook_13.ui.theme.White
import com.example.android_accountbook_13.ui.theme.White50

private var prevScreen: AccountBookDestination = History

@Composable
fun BottomAppBar(
    destination: AccountBookDestination?,
    backgroundColor: Color = Purple,
    elevation: Dp = 0.dp,
    onClick: (AccountBookDestination) -> Unit = {}
) {
    AccountBookTheme() {
        BottomAppBar(
            backgroundColor = backgroundColor,
            elevation = elevation
        ) {
            val nextScreen: AccountBookDestination = destination ?: prevScreen
            bottomTabScreens.forEach { screen ->
                ItemBottomAppBars(
                    screen,
                    Modifier.weight(1f),
                    if (nextScreen.route == screen.route) White else White50
                ) {
                    onClick(screen)
                }
            }
            prevScreen = nextScreen
        }
    }
}

@Composable
private fun ItemBottomAppBars(
    screen: AccountBookDestination,
    modifier: Modifier,
    color: Color,
    onClick: () -> Unit
) {
    Box(contentAlignment = Alignment.TopCenter, modifier = modifier.fillMaxHeight()) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = ImageVector.vectorResource(id = screen.vectorResource),
                contentDescription = screen.route,
                tint = color
            )
        }
        Text(
            text = screen.content,
            modifier = Modifier.align(Alignment.BottomCenter),
            style = MaterialTheme.typography.caption,
            color = color
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun BottomAppBarsPreview() {
    BottomAppBar(Calendar)
}
