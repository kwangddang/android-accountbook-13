package com.example.android_accountbook_13.data.dto

data class History(
    val id: Int? = null,
    val categoryId: Int,
    val methodId: Int,
    val name: String,
    val methodType: Int,
    val money: Int,
    val year: Int,
    val month: Int,
    val day: Int
)
