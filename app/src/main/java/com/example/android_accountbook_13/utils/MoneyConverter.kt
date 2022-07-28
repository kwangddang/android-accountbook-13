package com.example.android_accountbook_13.utils

fun moneyConverter(money: Int) : String {
    val moneyString = StringBuilder(money.toString()).reverse()
    val count = (moneyString.length - 1) / 3
    for(i in 1..count) {
        moneyString.insert(i * 3 + i - 1, ",")
    }
    return moneyString.reverse().toString()
}