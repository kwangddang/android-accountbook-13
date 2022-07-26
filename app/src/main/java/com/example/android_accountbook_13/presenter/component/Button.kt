package com.example.android_accountbook_13.presenter.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.ui.theme.*
import com.example.android_accountbook_13.utils.moneyConverter

@Composable
fun AddingButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(328.dp)
            .height(64.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Yellow)
    ) {
        Text(
            text = stringResource(id = R.string.text_btn),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
            color = White
        )
    }
}

@Composable
fun SwitchButton(
    incomeChecked: Boolean = true,
    expenseChecked: Boolean = false,
    onIncomeClick: () -> Unit = {},
    onExpenseClick: () -> Unit = {},
) {
    MyTheme {
        Row {
            ThinButton(
                shape = RoundedCornerShape(topStart = 14.dp, bottomStart = 14.dp),
                checked = incomeChecked,
                onClick = onIncomeClick
            ) {
                Text(
                    text = stringResource(id = R.string.text_income),
                    color = MaterialTheme.colors.onSecondary
                )
            }
            ThinButton(
                shape = RoundedCornerShape(topEnd = 14.dp, bottomEnd = 14.dp),
                checked = expenseChecked,
                onClick = onExpenseClick
            ) {
                Text(
                    text = stringResource(id = R.string.text_expense),
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }
    }
}


@Composable
fun FilterButton(
    incomeChecked: Boolean = true,
    expenseChecked: Boolean = false,
    incomeMoney: Long = 0,
    expenseMoney: Long = 0,
    onIncomeClick: () -> Unit = {},
    onExpenseClick: () -> Unit = {},
    onIncomeCheckedChange: (Boolean) -> Unit = {},
    onExpenseCheckedChange: (Boolean) -> Unit = {},
) {
    MyTheme {
        Row {
            ThinButton(
                shape = RoundedCornerShape(topStart = 14.dp, bottomStart = 14.dp),
                checked = incomeChecked,
                onClick = onIncomeClick
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    CheckedText(
                        title = stringResource(id = R.string.text_income),
                        money = incomeMoney,
                        checked = incomeChecked,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onCheckedChange = onIncomeCheckedChange
                    )
                }
            }
            ThinButton(
                shape = RoundedCornerShape(topEnd = 14.dp, bottomEnd = 14.dp),
                checked = expenseChecked,
                onClick = onExpenseClick
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    CheckedText(
                        title = stringResource(id = R.string.text_expense),
                        money = expenseMoney,
                        checked = expenseChecked,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onCheckedChange = onExpenseCheckedChange
                    )
                }
            }
        }
    }
}

@Composable
fun CheckedText(
    title: String,
    checked: Boolean,
    modifier: Modifier,
    money: Long = 0,
    onCheckedChange: (Boolean) -> Unit
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colors.onSecondary,
            uncheckedColor = MaterialTheme.colors.onSecondary,
            checkmarkColor = MaterialTheme.colors.secondary
        ),
        modifier = Modifier.size(24.dp)
    )
    Text(
        text = "${title} ${moneyConverter(money)}",
        modifier = modifier.padding(start = 8.dp),
        color = MaterialTheme.colors.onSecondary,
        style = MaterialTheme.typography.caption
    )
}

@Composable
fun ThinButton(
    shape: RoundedCornerShape,
    checked: Boolean,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(164.dp)
            .height(32.dp),
        shape = shape,
        colors = if (checked) ButtonDefaults.buttonColors(backgroundColor = Purple)
        else ButtonDefaults.buttonColors(backgroundColor = LightPurple),
        content = content
    )
}

@Preview()
@Composable
fun AddButtonPreview() {
    AddingButton {

    }
}

@Preview()
@Composable
fun SwitchButtonPreview() {
    SwitchButton()
}

@Preview()
@Composable
fun FilterButtonPreview() {
    FilterButton()
}