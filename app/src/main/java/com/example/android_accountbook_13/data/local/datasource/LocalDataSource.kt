package com.example.android_accountbook_13.data.local.datasource

import android.database.Cursor
import com.example.android_accountbook_13.data.HistoryItem
import com.example.android_accountbook_13.data.entity.Category
import com.example.android_accountbook_13.data.entity.PaymentMethod

interface LocalDataSource {
    fun getHistory(month: Int): Result<Cursor>
    fun insertHistory(historyItem: HistoryItem): Result<Unit>
    fun updateHistory(historyItem: HistoryItem): Result<Unit>
    fun deleteHistory(historyItem: HistoryItem): Result<Unit>

    fun getAllCategory(): Result<Cursor>
    fun getCategory(id: Int): Result<Cursor>
    fun insertCategory(category: Category): Result<Unit>
    fun updateCategory(category: Category): Result<Unit>
    fun deleteCategory(category: Category): Result<Unit>

    fun getAllPaymentMethod(): Result<Cursor>
    fun getPaymentMethod(id: Int): Result<Cursor>
    fun insertPaymentMethod(paymentMethod: PaymentMethod): Result<Unit>
    fun updatePaymentMethod(paymentMethod: PaymentMethod): Result<Unit>
    fun deletePaymentMethod(paymentMethod: PaymentMethod): Result<Unit>

}
