package com.example.android_accountbook_13.data.model

data class Payment(
    val id: Long,
    val categoryId: Long,
    val paymentMethodId: Long,
    val name: String,
    val methodType: Boolean,
    val money: Long,
    val year: Int,
    val month: Int,
    val day: Int
)
