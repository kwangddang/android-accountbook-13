package com.example.android_accountbook_13.data

import com.example.android_accountbook_13.data.entity.Category
import com.example.android_accountbook_13.data.entity.History
import com.example.android_accountbook_13.data.entity.Method

object DummyData {
    val accountBookItems = listOf<AccountBookItem>(
        AccountBookItem(
            History(0, 0, 0, "테스트1", 1, 10900, 2022, 7, 15),
            Category(0, "문화/여가", "#40B98D"),
            Method(0, "현대카드"),
            ),
        AccountBookItem(
            History(0, 0, 0, "테스트2", 1, 20900, 2022, 7, 15),
            Category(0, "테스트1", "#40198D"),
            Method(0, "테스트카드1"),
            ),
        AccountBookItem(
            History(0, 0, 0, "테스트3", 0, 30900, 2022, 7, 14),
            Category(0, "테스트2", "#40298D"),
            Method(0, "테스트카드2"),
            ),
        AccountBookItem(
            History(0, 0, 0, "테스트4", 1, 40900, 2022, 7, 14),
            Category(0, "테스트3", "#40398D"),
            Method(0, "테스트카드3"),
            ),
        AccountBookItem(
            History(0, 0, 0, "테스트5", 1, 50900, 2022, 7, 13),
            Category(0, "테스트4", "#40498D"),
            Method(0, "테스트카드4"),
        ),
        AccountBookItem(
            History(0, 0, 0, "테스트6", 0, 60900, 2022, 7, 12),
            Category(0, "테스트5", "#40598D"),
            Method(0, "테스트카드5"),
        ),
        AccountBookItem(
            History(0, 0, 0, "테스트7", 1, 70900, 2022, 7, 11),
            Category(0, "테스트6", "#40698D"),
            Method(0, "테스트카드6"),
        ),
        AccountBookItem(
            History(0, 0, 0, "테스트8", 1, 80900, 2022, 7, 11),
            Category(0, "테스트7", "#40798D"),
            Method(0, "테스트카드7"),
        ),
        AccountBookItem(
            History(0, 0, 0, "스트리밍 서비스 정기 결제", 1, 10900, 2022, 7, 15),
            Category(0, "테스트8", "#40898D"),
            Method(0, "테스트카드8"),
        ),
        AccountBookItem(
            History(0, 0, 0, "테스트9", 0, 90900, 2022, 7, 10),
            Category(0, "테스트9", "#40998D"),
            Method(0, "테스트카드9"),
        )
    )

}