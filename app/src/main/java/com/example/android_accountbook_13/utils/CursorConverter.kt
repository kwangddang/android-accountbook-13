package com.example.android_accountbook_13.utils

import android.database.Cursor
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.dto.Method

fun getHistoryFromCursor(cursor: Cursor,startIndex: Int): History {
    var index = startIndex
    val id = cursor.getInt(index++)
    val categoryId = cursor.getInt(index++)
    val methodId = cursor.getInt(index++)
    val name = cursor.getString(index++)
    val methodType = cursor.getInt(index++)
    val money = cursor.getLong(index++)
    val year = cursor.getInt(index++)
    val month = cursor.getInt(index++)
    val day = cursor.getInt(index++)
    return History(id, categoryId, methodId, name, methodType, money, year, month, day)
}

fun getCategoryFromCursor(cursor: Cursor, startIndex: Int): Category {
    var index = startIndex
    val id = cursor.getInt(index++)
    val name = cursor.getString(index++)
    val color = cursor.getString(index++)
    val type = cursor.getInt(index++)

    return Category(id,name, color, type)
}

fun getMethodFromCursor(cursor: Cursor,startIndex: Int): Method {
    var index = startIndex
    val id = cursor.getInt(index++)
    val name = cursor.getString(index++)

    return Method(id,name)
}