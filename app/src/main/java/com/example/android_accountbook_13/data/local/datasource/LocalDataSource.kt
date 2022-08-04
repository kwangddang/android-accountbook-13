package com.example.android_accountbook_13.data.local.datasource

import android.database.Cursor
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.dto.Method

interface LocalDataSource {
    suspend fun insertHistory(history: History): Result<Unit>
    suspend fun updateHistory(history: History): Result<Unit>
    suspend fun deleteHistory(historyIds: List<Int>): Result<Unit>

    suspend fun getIncomeCategory(): Result<Cursor>
    suspend fun getExpenseCategory(): Result<Cursor>
    suspend fun insertCategory(category: Category): Result<Unit>
    suspend fun updateCategory(category: Category): Result<Unit>

    suspend fun getAllMethod(): Result<Cursor>
    suspend fun insertMethod(method: Method): Result<Unit>
    suspend fun updateMethod(method: Method): Result<Unit>

    suspend fun getAccountBook(year: Int, month: Int): Result<Cursor>
}
