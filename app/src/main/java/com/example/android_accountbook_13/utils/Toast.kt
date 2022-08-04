package com.example.android_accountbook_13.utils

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, message: String?) {
    Toast.makeText(context,message ?: "오류가 발생하였습니다.",Toast.LENGTH_SHORT).show()
}