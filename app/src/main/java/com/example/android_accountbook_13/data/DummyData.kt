package com.example.android_accountbook_13.data

import com.example.android_accountbook_13.data.entity.Category
import com.example.android_accountbook_13.data.entity.Payment
import com.example.android_accountbook_13.data.entity.PaymentMethod

object DummyData {
    val historyItem = listOf<HistoryItem>(
        HistoryItem(
            Payment(0, 0, 0, "테스트1", true, 10900, 2022, 7, 15),
            Category(0, "문화/여가", 0xFF40B98D),
            PaymentMethod(0, "현대카드"),
            ),
        HistoryItem(
            Payment(0, 0, 0, "테스트2", true, 20900, 2022, 7, 15),
            Category(0, "테스트1", 0xFF40B98D),
            PaymentMethod(0, "테스트카드1"),
            ),
        HistoryItem(
            Payment(0, 0, 0, "테스트3", false, 30900, 2022, 7, 14),
            Category(0, "테스트2", 0xFF40198D),
            PaymentMethod(0, "테스트카드2"),
            ),
        HistoryItem(
            Payment(0, 0, 0, "테스트4", true, 40900, 2022, 7, 14),
            Category(0, "테스트3", 0xFF40298D),
            PaymentMethod(0, "테스트카드3"),
            ),
        HistoryItem(
            Payment(0, 0, 0, "테스트5", true, 50900, 2022, 7, 13),
            Category(0, "테스트4", 0xFF40398D),
            PaymentMethod(0, "테스트카드4"),
        ),
        HistoryItem(
            Payment(0, 0, 0, "테스트6", false, 60900, 2022, 7, 12),
            Category(0, "테스트5", 0xFF40498D),
            PaymentMethod(0, "테스트카드5"),
        ),
        HistoryItem(
            Payment(0, 0, 0, "테스트7", true, 70900, 2022, 7, 11),
            Category(0, "테스트6", 0xFF40598D),
            PaymentMethod(0, "테스트카드6"),
        ),
        HistoryItem(
            Payment(0, 0, 0, "테스트8", true, 80900, 2022, 7, 11),
            Category(0, "테스트7", 0xFF40698D),
            PaymentMethod(0, "테스트카드7"),
        ),
        HistoryItem(
            Payment(0, 0, 0, "스트리밍 서비스 정기 결제", true, 10900, 2022, 7, 15),
            Category(0, "테스트8", 0xFF40798D),
            PaymentMethod(0, "테스트카드8"),
        ),
        HistoryItem(
            Payment(0, 0, 0, "테스트9", false, 90900, 2022, 7, 10),
            Category(0, "테스트9", 0xFF40898D),
            PaymentMethod(0, "테스트카드9"),
        )
    )

}