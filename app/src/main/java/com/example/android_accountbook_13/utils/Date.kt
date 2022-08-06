package com.example.android_accountbook_13.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.threeten.bp.LocalDate

@Parcelize
data class Date(
    var year : Int,
    var month : Int,
    var day : Int
): Parcelable

fun getCurrentDate(): Date {
    val current = LocalDate.now().toString()
    val date = current.split("-")
    return Date(date[0].toInt(),date[1].toInt(),date[2].toInt())
}

fun getDayOfWeek(date: Date): String {
    val dayOfWeekName = arrayOf("월요일","화요일","수요일","목요일","금요일","토요일","일요일")
    val number = getDayOfWeekNumber(date)
    return dayOfWeekName[number]
}

fun getDayOfWeekNumber(date: Date): Int {
    val date = LocalDate.of(date.year,date.month,date.day)
    val dayOfWeek = date.dayOfWeek
    return dayOfWeek.value - 1
}

fun getYearMonthString(date: Date): String {
    return "${date.year}년 ${date.month}월"
}

fun getYearMonthDayString(date: Date): String {
    return "${date.year}년 ${date.month}월 ${date.day}일 ${getDayOfWeek(date)}"
}

fun getStringToDate(str : String): Date {
    val date = str.split(" ")
    val year = date[0].substring(0,4).toInt()
    val month = date[1].split("월")[0].toInt()
    return Date(year,month,0)
}

fun increaseDate(date: Date): Date {
    val newDate = Date(date.year, date.month, date.day)
    newDate.month++
    if(newDate.month == 13){
        newDate.month = 1
        newDate.year++
    }
    return newDate
}

fun decreaseDate(date: Date): Date {
    val newDate = Date(date.year, date.month, date.day)
    newDate.month--
    if(newDate.month == 0){
        newDate.month = 12
        newDate.year--
    }
    return newDate
}

fun getEndOfMonth(date: Date): Int {
    return LocalDate.of(date.year,date.month,date.day).lengthOfMonth()
}