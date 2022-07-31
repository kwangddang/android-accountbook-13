package com.example.android_accountbook_13.data.local.datasource

import android.database.Cursor
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.dto.Method

interface LocalDataSource {
    fun getHistory(year: Int, month: Int): Result<Cursor>
    fun insertHistory(history: History): Result<Unit>
    fun updateHistory(history: History): Result<Unit>
    fun deleteHistory(historyId: Int): Result<Unit>

    fun getAllCategory(): Result<Cursor>
    fun getIncomeCategory(): Result<Cursor>
    fun getExpenseCategory(): Result<Cursor>
    fun insertCategory(category: Category): Result<Unit>
    fun updateCategory(category: Category): Result<Unit>
    fun deleteCategory(categoryId: Int): Result<Unit>

    fun getAllMethod(): Result<Cursor>
    fun getMethod(id: Int): Result<Cursor>
    fun insertMethod(method: Method): Result<Unit>
    fun updateMethod(method: Method): Result<Unit>
    fun deleteMethod(methodId: Int): Result<Unit>

    fun getAccountBook(year: Int, month: Int): Result<Cursor>
}
