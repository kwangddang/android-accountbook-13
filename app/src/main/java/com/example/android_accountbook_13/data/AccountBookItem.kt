package com.example.android_accountbook_13.data

import com.example.android_accountbook_13.data.entity.Category
import com.example.android_accountbook_13.data.entity.History
import com.example.android_accountbook_13.data.entity.Method

data class AccountBookItem(
    val history: History,
    val category: Category,
    val method: Method,
)