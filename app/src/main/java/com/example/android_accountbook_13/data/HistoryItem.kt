package com.example.android_accountbook_13.data

import com.example.android_accountbook_13.data.entity.Category
import com.example.android_accountbook_13.data.entity.Payment
import com.example.android_accountbook_13.data.entity.PaymentMethod

data class HistoryItem(
    val payment: Payment,
    val category: Category,
    val paymentMethod: PaymentMethod,
)