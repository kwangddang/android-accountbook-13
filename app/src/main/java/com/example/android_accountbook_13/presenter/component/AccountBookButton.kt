package com.example.android_accountbook_13.presenter.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.ui.theme.*
import com.example.android_accountbook_13.utils.moneyConverter

data class RadiusDP(
    val topStart: Dp = 14.dp,
    val bottomStart: Dp = 14.dp,
    val topEnd: Dp = 14.dp,
    val bottomEnd: Dp = 14.dp,
)

@Composable
fun AccountBookAddingButton(
    title: String = stringResource(id = R.string.text_btn),
    style: TextStyle = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
    modifier: Modifier = Modifier
        .width(328.dp)
        .height(64.dp),
    shape: RoundedCornerShape = RoundedCornerShape(14.dp),
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(Yellow),
    textColor: Color = White,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = buttonColors
    ) {
        Text(
            text = title,
            style = style,
            color = textColor
        )
    }
}

@Composable
fun AccountBookSwitchButton(
    leftChecked: Boolean,
    rightChecked: Boolean,
    leftString: String = stringResource(id = R.string.text_income),
    rightString: String = stringResource(id = R.string.text_expense),
    textColor: Color = White,
    enabledColor: Color = Purple,
    unEnabledColor: Color = LightPurple,
    radiusDP: RadiusDP = RadiusDP(),
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {},
) {
    AccountBookTheme {
        Row {
            ThinButton(
                shape = RoundedCornerShape(
                    topStart = radiusDP.topStart,
                    bottomStart = radiusDP.bottomStart
                ),
                checked = leftChecked,
                onClick = onLeftClick,
                enabledColor = enabledColor,
                unEnabledColor = unEnabledColor
            ) {
                Text(
                    text = leftString,
                    color = textColor
                )
            }
            ThinButton(
                shape = RoundedCornerShape(
                    topEnd = radiusDP.topEnd,
                    bottomEnd = radiusDP.bottomEnd
                ),
                checked = rightChecked,
                onClick = onRightClick,
                enabledColor = enabledColor,
                unEnabledColor = unEnabledColor
            ) {
                Text(
                    text = rightString,
                    color = textColor
                )
            }
        }
    }
}


@Composable
fun AccountBookFilterButton(
    leftChecked: Boolean,
    rightChecked: Boolean,
    leftMoney: Long,
    rightMoney: Long,
    radiusDP: RadiusDP = RadiusDP(),
    checkedColor : Color = White,
    uncheckedColor : Color = White,
    checkmarkColor : Color = Purple,
    enabledColor: Color = Purple,
    unEnabledColor: Color = LightPurple,
    textColor: Color = White,
    textStyle: TextStyle = MaterialTheme.typography.caption,
    leftString: String = stringResource(id = R.string.text_income),
    rightString: String = stringResource(id = R.string.text_expense),
    modifier: Modifier = Modifier,
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {},
    onLeftCheckedChange: (Boolean) -> Unit = {},
    onRightCheckedChange: (Boolean) -> Unit = {},
) {
    AccountBookTheme {
        Row {
            ThinButton(
                shape = RoundedCornerShape(
                    topStart = radiusDP.topStart,
                    bottomStart = radiusDP.bottomStart
                ),
                checked = leftChecked,
                onClick = onLeftClick,
                enabledColor = enabledColor,
                unEnabledColor = unEnabledColor
            ) {
                Row(modifier = modifier.fillMaxWidth()) {
                    CheckedText(
                        title = leftString,
                        money = leftMoney,
                        checked = leftChecked,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onCheckedChange = onLeftCheckedChange,
                        checkedColor = checkedColor,
                        uncheckedColor = uncheckedColor,
                        checkmarkColor = checkmarkColor,
                        textColor = textColor,
                        textStyle = textStyle
                    )
                }
            }
            ThinButton(
                shape = RoundedCornerShape(
                    topEnd = radiusDP.topEnd,
                    bottomEnd = radiusDP.bottomEnd
                ),
                checked = rightChecked,
                onClick = onRightClick,
                enabledColor = enabledColor,
                unEnabledColor = unEnabledColor
            ) {
                Row(modifier = modifier.fillMaxWidth()) {
                    CheckedText(
                        title = rightString,
                        money = rightMoney,
                        checked = rightChecked,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onCheckedChange = onRightCheckedChange,
                        checkedColor = checkedColor,
                        uncheckedColor = uncheckedColor,
                        checkmarkColor = checkmarkColor,
                        textColor = textColor,
                        textStyle = textStyle
                    )
                }
            }
        }
    }
}

@Composable
private fun CheckedText(
    title: String,
    checked: Boolean,
    modifier: Modifier,
    money: Long,
    checkedColor : Color,
    uncheckedColor : Color,
    checkmarkColor : Color,
    textColor: Color,
    textStyle: TextStyle,
    onCheckedChange: (Boolean) -> Unit
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = CheckboxDefaults.colors(
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor,
            checkmarkColor = checkmarkColor
        ),
        modifier = Modifier.size(24.dp)
    )
    Text(
        text = "${title} ${moneyConverter(money)}",
        modifier = modifier.padding(start = 8.dp),
        color = textColor,
        style = textStyle
    )
}

@Composable
private fun ThinButton(
    shape: RoundedCornerShape,
    checked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.width(164.dp).height(32.dp),
    enabledColor: Color,
    unEnabledColor: Color,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = if (checked) ButtonDefaults.buttonColors(backgroundColor = enabledColor)
        else ButtonDefaults.buttonColors(backgroundColor = unEnabledColor),
        content = content
    )
}

@Preview()
@Composable
private fun AddButtonPreview() {
    AccountBookAddingButton {

    }
}

@Preview()
@Composable
private fun SwitchButtonPreview() {
    AccountBookSwitchButton(leftChecked = false, rightChecked = true)
}

@Preview()
@Composable
private fun FilterButtonPreview() {
    AccountBookFilterButton(
        leftChecked = false,
        rightChecked = true,
        leftMoney = 1000,
        rightMoney = 200000,
    )
}