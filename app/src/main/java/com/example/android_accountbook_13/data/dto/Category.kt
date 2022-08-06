package com.example.android_accountbook_13.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int?,
    val name: String,
    val color: String,
    val type: Int
): Parcelable
