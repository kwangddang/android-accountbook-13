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
import com.example.android_accountbook_13.data.HistoryItem
import com.example.android_accountbook_13.data.entity.Category
import com.example.android_accountbook_13.data.entity.Payment
import com.example.android_accountbook_13.data.entity.PaymentMethod
import com.example.android_accountbook_13.ui.theme.*
import com.example.android_accountbook_13.utils.moneyConverter

@Composable
fun AccountBookHistoryItemContent(
    historyItem: HistoryItem,
    onClick: () -> Unit,
    onLongClick: () -> Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            AccountBookCategory(
                title = historyItem.category.name,
                backgroundColor = Color(android.graphics.Color.parseColor(historyItem.category.color)),
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Text(text = historyItem.paymentMethod.name, fontSize = 12.sp, color = Purple, modifier = Modifier.align(Alignment.CenterEnd))
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = historyItem.payment.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Purple,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 8.dp)
            )
            var color: Color
            var text: String
            if (historyItem.payment.methodType == 1) {
                color = Green6
                text = "${moneyConverter(historyItem.payment.money)}원"
            } else {
                color = Red
                text = "-${moneyConverter(historyItem.payment.money)}원"
            }
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = color,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun AccountBookHistoryItemHeader(
    date: String,
    income: Int,
    expense: Int,
    leftChecked: Boolean,
    rightChecked: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 16.dp, end = 16.dp),
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
                if(leftChecked){
                    HeaderText("수입")
                    HeaderText(moneyConverter(income))
                }
                if(rightChecked){
                    HeaderText("지출")
                    HeaderText(moneyConverter(expense))
                }
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
private fun AccountBookHistoryItemContentPreview() {
    AccountBookHistoryItemContent(
        historyItem = HistoryItem(
            payment = Payment(0, 0, 0, "스트리밍 서비스 정기 결제", 1, 10900, 2022, 7, 15),
            category = Category(0, "문화/여가", "#40B98D"),
            paymentMethod = PaymentMethod(0, "현대카드"),
        ),
        onClick = { /*TODO*/ }) {
        true
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountBookHistoryHeaderPreview() {
    AccountBookHistoryItemHeader("7월 15일 금", 0, 56240,true,true)
}