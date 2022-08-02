package com.example.android_accountbook_13.utils

fun moneyConverter(money: Long) : String {
    val moneyString = StringBuilder(money.toString()).reverse()
    if(money < 0) moneyString.deleteCharAt(moneyString.lastIndex)
    val count = (moneyString.length - 1) / 3
    for(i in 1..count) {
        moneyString.insert(i * 3 + i - 1, ",")
    }
    return if(money < 0)
        "-${moneyString.reverse()}"
    else
        moneyString.reverse().toString()
}