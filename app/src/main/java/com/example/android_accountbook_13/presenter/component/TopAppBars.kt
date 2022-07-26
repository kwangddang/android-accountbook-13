package com.example.android_accountbook_13.presenter.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.ui.theme.MyTheme
import com.example.android_accountbook_13.ui.theme.Red

@Composable
fun TopAppBars(
    title: String,
    leftVectorResource: Int? = null,
    rightVectorResource: Int? = null,
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {}
) {
    MyTheme() {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground,
            elevation = 1.dp
        ) {
            Box {
                if (leftVectorResource != null) {
                    IconTopAppBar(
                        onLeftClick,
                        Modifier.align(Alignment.CenterStart),
                        leftVectorResource
                    )
                }
                TitleTopAppBar(
                    title,
                    Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                )
                if (rightVectorResource != null) {
                    IconTopAppBar(
                        onRightClick,
                        Modifier.align(Alignment.CenterEnd),
                        rightVectorResource
                    )
                }
            }
        }
    }
}

@Composable
fun IconTopAppBar(
    onClick: () -> Unit,
    modifier: Modifier,
    vectorResource: Int
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = vectorResource),
            contentDescription = stringResource(id = R.string.desc_back),
            tint = if (vectorResource == R.drawable.ic_trash) Red
                    else MaterialTheme.colors.secondary
        )
    }
}

@Composable
fun TitleTopAppBar(
    title: String,
    modifier: Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.subtitle1,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun BaseTopAppBarPreview() {
    TopAppBars("Toolbar")
}

@Preview(showBackground = true)
@Composable
fun BackTopAppBarPreview() {
    TopAppBars("설정", R.drawable.ic_back)
}

@Preview(showBackground = true)
@Composable
fun DateTopAppBarPreview() {
    TopAppBars("2022년 7월", R.drawable.ic_left, R.drawable.ic_right)
}

@Preview(showBackground = true)
@Composable
fun TrashTopAppBarPreview() {
    TopAppBars("1개 선택", R.drawable.ic_back, R.drawable.ic_trash)
}
