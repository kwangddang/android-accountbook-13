package com.example.android_accountbook_13.presenter.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_accountbook_13.data.model.Category
import com.example.android_accountbook_13.data.model.Payment
import com.example.android_accountbook_13.data.model.PaymentMethod
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.utils.moneyConverter

@Composable
fun AccountBookHistoryItem(
    payment: Payment,
    category: Category,
    paymentMethod: PaymentMethod,
    title: String,
    onClick: () -> Unit,
    onLongClick: () -> Boolean
) {

}

@Composable
fun AccountBookHistoryItemHeader(
    date: String,
    income: Long,
    expense: Long
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = date, fontSize = 18.sp, color = LightPurple)
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                HeaderText("수입")
                HeaderText(moneyConverter(income))
                HeaderText("지출")
                HeaderText(moneyConverter(expense))
            }
        }
        Divider(color = LightPurple, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
private fun HeaderText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.caption,
        color = LightPurple,
        modifier = Modifier.padding(start = 8.dp)
    )
}

@Preview
@Composable
fun AccountBookHistoryHeaderPreview() {
    AccountBookHistoryItemHeader("7월 15일 금", 0, 56240)
}