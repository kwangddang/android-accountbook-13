package com.example.android_accountbook_13.data.entity

data class Payment(
    val id: Int,
    val categoryId: Int,
    val paymentMethodId: Int,
    val name: String,
    val methodType: Boolean,
    val money: Int,
    val year: Int,
    val month: Int,
    val day: Int
)
