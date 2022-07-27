package com.example.android_accountbook_13.presenter.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_accountbook_13.data.model.Category
import com.example.android_accountbook_13.data.model.Payment
import com.example.android_accountbook_13.data.model.PaymentMethod
import com.example.android_accountbook_13.ui.theme.*
import com.example.android_accountbook_13.utils.moneyConverter

@Composable
fun AccountBookHistoryItemContent(
    payment: Payment,
    category: Category,
    paymentMethod: PaymentMethod,
    onClick: () -> Unit,
    onLongClick: () -> Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            AccountBookCategory(
                title = category.name,
                backgroundColor = Color(category.color),
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Text(text = paymentMethod.name, fontSize = 12.sp, color = Purple, modifier = Modifier.align(Alignment.CenterEnd))
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = payment.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Purple,
                modifier = Modifier.align(Alignment.CenterStart).padding(top = 8.dp)
            )
            var color: Color
            var text: String
            if(payment.methodType) {
                color = Green6
                text = "${moneyConverter(payment.money)}원"
            } else {
                color = Red
                text = "-${moneyConverter(payment.money)}원"
            }
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = color,
                modifier = Modifier.align(Alignment.CenterEnd).padding(top = 8.dp)
            )
        }
        Divider(color = LightPurple, modifier = Modifier.padding(top = 8.dp))
    }
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

@Preview(showBackground = true)
@Composable
fun AccountBookHistoryItemContent() {
    AccountBookHistoryItemContent(
        payment = Payment(0, 0, 0, "스트리밍 서비스 정기 결제", true, 10900, 2022, 7, 15),
        category = Category(0, "문화/여가", 0xFF40B98D),
        paymentMethod = PaymentMethod(0, "현대카드"),
        onClick = { /*TODO*/ }) {
        true
    }
}

@Preview(showBackground = true)
@Composable
fun AccountBookHistoryHeaderPreview() {
    AccountBookHistoryItemHeader("7월 15일 금", 0, 56240)
}