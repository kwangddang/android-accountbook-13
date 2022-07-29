package com.example.android_accountbook_13.data.local.datasource

import android.database.Cursor
import com.example.android_accountbook_13.data.AccountBookItem
import com.example.android_accountbook_13.data.entity.Category
import com.example.android_accountbook_13.data.entity.History
import com.example.android_accountbook_13.data.entity.Method

interface LocalDataSource {
    fun getHistory(month: Int): Result<Cursor>
    fun insertHistory(history: History): Result<Unit>
    fun updateHistory(history:History): Result<Unit>
    fun deleteHistory(history:History): Result<Unit>

    fun getAllCategory(): Result<Cursor>
    fun getCategory(id: Int): Result<Cursor>
    fun insertCategory(category: Category): Result<Unit>
    fun updateCategory(category: Category): Result<Unit>
    fun deleteCategory(category: Category): Result<Unit>

    fun getAllMethod(): Result<Cursor>
    fun getMethod(id: Int): Result<Cursor>
    fun insertMethod(method: Method): Result<Unit>
    fun updateMethod(method: Method): Result<Unit>
    fun deleteMethod(method: Method): Result<Unit>
}
