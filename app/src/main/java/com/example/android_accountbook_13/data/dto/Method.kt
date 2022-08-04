package com.example.android_accountbook_13.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Method(
    val id: Int?,
    val name: String
): Parcelable
