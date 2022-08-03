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
fun AddingButton(
    title: String = stringResource(id = R.string.btn_add),
    style: TextStyle = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
    modifier: Modifier = Modifier
        .width(328.dp)
        .height(64.dp),
    enabled: Boolean,
    shape: RoundedCornerShape = RoundedCornerShape(14.dp),
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(Yellow),
    textColor: Color = White,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = buttonColors,
        enabled = enabled
    ) {
        Text(
            text = title,
            style = style,
            color = textColor
        )
    }
}

@Composable
fun SwitchButton(
    incomeChecked: Boolean,
    expenseChecked: Boolean,
    textColor: Color = White,
    enabledColor: Color = Purple,
    unEnabledColor: Color = LightPurple,
    radiusDP: RadiusDP = RadiusDP(),
    onIncomeClick: () -> Unit = {},
    onExpenseClick: () -> Unit = {},
) {
    AccountBookTheme {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            ThinButton(
                shape = RoundedCornerShape(
                    topStart = radiusDP.topStart,
                    bottomStart = radiusDP.bottomStart
                ),
                checked = incomeChecked,
                onClick = onIncomeClick,
                enabledColor = enabledColor,
                unEnabledColor = unEnabledColor
            ) {
                Text(
                    text = stringResource(id = R.string.income),
                    color = textColor
                )
            }
            ThinButton(
                shape = RoundedCornerShape(
                    topEnd = radiusDP.topEnd,
                    bottomEnd = radiusDP.bottomEnd
                ),
                checked = expenseChecked,
                onClick = onExpenseClick,
                enabledColor = enabledColor,
                unEnabledColor = unEnabledColor
            ) {
                Text(
                    text = stringResource(id = R.string.expense),
                    color = textColor
                )
            }
        }
    }
}


@Composable
fun FilterButton(
    incomeChecked: Boolean,
    expenseChecked: Boolean,
    incomeMoney: Long,
    expenseMoney: Long,
    radiusDP: RadiusDP = RadiusDP(),
    checkedColor : Color = White,
    uncheckedColor : Color = White,
    checkmarkColor : Color = Purple,
    enabledColor: Color = Purple,
    unEnabledColor: Color = LightPurple,
    textColor: Color = White,
    textStyle: TextStyle = MaterialTheme.typography.caption,
    modifier: Modifier = Modifier,
    onIncomeClick: () -> Unit,
    onExpenseClick: () -> Unit,
    onIncomeCheckedChange: (Boolean) -> Unit,
    onExpenseCheckedChange: (Boolean) -> Unit,
) {
    AccountBookTheme {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16 .dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ThinButton(
                shape = RoundedCornerShape(
                    topStart = radiusDP.topStart,
                    bottomStart = radiusDP.bottomStart
                ),
                checked = incomeChecked,
                onClick = onIncomeClick,
                enabledColor = enabledColor,
                unEnabledColor = unEnabledColor
            ) {
                Row(modifier = modifier.fillMaxWidth()) {
                    CheckedText(
                        title = stringResource(id = R.string.income),
                        money = incomeMoney,
                        checked = incomeChecked,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onCheckedChange = onIncomeCheckedChange,
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
                checked = expenseChecked,
                onClick = onExpenseClick,
                enabledColor = enabledColor,
                unEnabledColor = unEnabledColor
            ) {
                Row(modifier = modifier.fillMaxWidth()) {
                    CheckedText(
                        title = stringResource(id = R.string.expense),
                        money = expenseMoney,
                        checked = expenseChecked,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onCheckedChange = onExpenseCheckedChange,
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
    modifier: Modifier = Modifier.width(164.dp).height(40.dp),
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
private fun AddingButtonPreview() {
    AddingButton(enabled = true) {

    }
}

@Preview()
@Composable
private fun SwitchButtonPreview() {
    SwitchButton(incomeChecked = false, expenseChecked = true)
}

@Preview()
@Composable
private fun FilterButtonPreview() {
    FilterButton(
        incomeChecked = false,
        expenseChecked = true,
        incomeMoney = 1000,
        expenseMoney = 200000,
        onExpenseClick = {},
        onIncomeClick = {},
        onIncomeCheckedChange = {},
        onExpenseCheckedChange = {}
    )
}