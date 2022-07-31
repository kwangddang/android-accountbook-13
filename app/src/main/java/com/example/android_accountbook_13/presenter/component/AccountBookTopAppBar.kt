package com.example.android_accountbook_13.presenter.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.ui.theme.AccountBookTheme
import com.example.android_accountbook_13.ui.theme.OffWhite
import com.example.android_accountbook_13.ui.theme.Purple
import com.example.android_accountbook_13.ui.theme.Red

@Composable
fun AccountBookTopAppBar(
    title: String,
    leftVectorResource: Int? = null,
    rightVectorResource: Int? = null,
    backgroundColor: Color = OffWhite,
    contentColor: Color = Purple,
    elevation: Dp = 0.dp,
    leftDescription: String = "",
    rightDescription: String = "",
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {}
) {
    AccountBookTheme() {
        TopAppBar(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            elevation = elevation
        ) {
            Box() {
                if (leftVectorResource != null) {
                    IconTopAppBar(
                        modifier = Modifier.align(Alignment.CenterStart),
                        vectorResource =  leftVectorResource,
                        contentDescription = leftDescription,
                        contentColor = contentColor,
                        onClick = onLeftClick
                    )
                }
                TitleTopAppBar(
                    title,
                    Modifier.align(Alignment.Center)
                )
                if (rightVectorResource != null) {
                    IconTopAppBar(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        vectorResource =  rightVectorResource,
                        contentDescription = rightDescription,
                        contentColor = contentColor,
                        onClick = onRightClick
                    )
                }
                Divider(
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Composable
private fun IconTopAppBar(
    contentDescription: String,
    contentColor: Color,
    modifier: Modifier,
    vectorResource: Int,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = vectorResource),
            contentDescription = contentDescription,
            tint = if (vectorResource == R.drawable.ic_trash) Red
                    else contentColor
        )
    }
}

@Composable
private fun TitleTopAppBar(
    title: String,
    modifier: Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        modifier = modifier.fillMaxHeight().padding(top = 14.dp, bottom = 14.dp),
        color = Purple
    )
}

@Preview(showBackground = true)
@Composable
private fun BaseTopAppBarPreview() {
    AccountBookTopAppBar(title = "Toolbar", onLeftClick = { }, onRightClick = {})
}

@Preview(showBackground = true)
@Composable
private fun BackTopAppBarPreview() {
    AccountBookTopAppBar("설정", R.drawable.ic_back, onLeftClick = {}, onRightClick = {})
}

@Preview(showBackground = true)
@Composable
private fun DateTopAppBarPreview() {
    AccountBookTopAppBar("2022년 7월", R.drawable.ic_left, R.drawable.ic_right, onLeftClick = {}, onRightClick = {})
}

@Preview(showBackground = true)
@Composable
private fun TrashTopAppBarPreview() {
    AccountBookTopAppBar("1개 선택", R.drawable.ic_back, R.drawable.ic_trash, onLeftClick = {}, onRightClick = {})
}
