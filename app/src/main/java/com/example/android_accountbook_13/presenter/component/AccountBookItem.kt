package com.example.android_accountbook_13.presenter.component

import androidx.compose.foundation.clickable
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
import com.example.android_accountbook_13.data.dto.AccountBookItem
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.ui.theme.Green6
import com.example.android_accountbook_13.ui.theme.LightPurple
import com.example.android_accountbook_13.ui.theme.Purple
import com.example.android_accountbook_13.ui.theme.Red
import com.example.android_accountbook_13.utils.moneyConverter

@Composable
fun AccountBookItemContent(
    accountBookItem: AccountBookItem,
    onClick: () -> Unit,
    onLongClick: () -> Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            AccountBookCategory(
                title = accountBookItem.category.name,
                backgroundColor = Color(android.graphics.Color.parseColor(accountBookItem.category.color)),
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Text(text = accountBookItem.method.name, fontSize = 12.sp, color = Purple, modifier = Modifier.align(Alignment.CenterEnd))
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = accountBookItem.history.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Purple,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 8.dp)
            )
            var color: Color
            var text: String
            if (accountBookItem.history.methodType == 1) {
                color = Green6
                text = "${moneyConverter(accountBookItem.history.money)}원"
            } else {
                color = Red
                text = "-${moneyConverter(accountBookItem.history.money)}원"
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
fun AccountBookItemHeader(
    date: String,
    income: Long,
    expense: Long,
    incomeChecked: Boolean,
    expenseChecked: Boolean,
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
                if(incomeChecked){
                    HeaderText("수입")
                    HeaderText(moneyConverter(income))
                }
                if(expenseChecked){
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
private fun AccountBookItemContentPreview() {
    AccountBookItemContent(
        accountBookItem = AccountBookItem(
            history = History( 0, 0, 0, "스트리밍 서비스 정기 결제", 1, 10900, 2022, 7, 15),
            category = Category(0, "문화/여가", "#40B98D",0),
            method = Method(0, "현대카드"),
        ),
        onClick = { /*TODO*/ }) {
        true
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountBookHistoryHeaderPreview() {
    AccountBookItemHeader("7월 15일 금", 0, 56240,true,true)
}