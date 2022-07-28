package com.example.android_accountbook_13.data

import com.example.android_accountbook_13.data.entity.Category
import com.example.android_accountbook_13.data.entity.Payment
import com.example.android_accountbook_13.data.entity.PaymentMethod

object DummyData {
    val historyItem = listOf<HistoryItem>(
        HistoryItem(
            Payment(0, 0, 0, "테스트1", 1, 10900, 2022, 7, 15),
            Category(0, "문화/여가", "#40B98D"),
            PaymentMethod(0, "현대카드"),
            ),
        HistoryItem(
            Payment(0, 0, 0, "테스트2", 1, 20900, 2022, 7, 15),
            Category(0, "테스트1", "#40198D"),
            PaymentMethod(0, "테스트카드1"),
            ),
        HistoryItem(
            Payment(0, 0, 0, "테스트3", 0, 30900, 2022, 7, 14),
            Category(0, "테스트2", "#40298D"),
            PaymentMethod(0, "테스트카드2"),
            ),
        HistoryItem(
            Payment(0, 0, 0, "테스트4", 1, 40900, 2022, 7, 14),
            Category(0, "테스트3", "#40398D"),
            PaymentMethod(0, "테스트카드3"),
            ),
        HistoryItem(
            Payment(0, 0, 0, "테스트5", 1, 50900, 2022, 7, 13),
            Category(0, "테스트4", "#40498D"),
            PaymentMethod(0, "테스트카드4"),
        ),
        HistoryItem(
            Payment(0, 0, 0, "테스트6", 0, 60900, 2022, 7, 12),
            Category(0, "테스트5", "#40598D"),
            PaymentMethod(0, "테스트카드5"),
        ),
        HistoryItem(
            Payment(0, 0, 0, "테스트7", 1, 70900, 2022, 7, 11),
            Category(0, "테스트6", "#40698D"),
            PaymentMethod(0, "테스트카드6"),
        ),
        HistoryItem(
            Payment(0, 0, 0, "테스트8", 1, 80900, 2022, 7, 11),
            Category(0, "테스트7", "#40798D"),
            PaymentMethod(0, "테스트카드7"),
        ),
        HistoryItem(
            Payment(0, 0, 0, "스트리밍 서비스 정기 결제", 1, 10900, 2022, 7, 15),
            Category(0, "테스트8", "#40898D"),
            PaymentMethod(0, "테스트카드8"),
        ),
        HistoryItem(
            Payment(0, 0, 0, "테스트9", 0, 90900, 2022, 7, 10),
            Category(0, "테스트9", "#40998D"),
            PaymentMethod(0, "테스트카드9"),
        )
    )

}