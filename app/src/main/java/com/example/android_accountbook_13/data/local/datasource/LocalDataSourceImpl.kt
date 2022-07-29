package com.example.android_accountbook_13.data.local.datasource

import android.database.Cursor
import com.example.android_accountbook_13.data.HistoryItem
import com.example.android_accountbook_13.data.entity.Category
import com.example.android_accountbook_13.data.entity.PaymentMethod
import com.example.android_accountbook_13.data.local.SQLiteOpenHelper
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val db: SQLiteOpenHelper
): LocalDataSource {
    /**
     * History
     */
    override fun getHistory(month: Int): Result<Cursor> =
        runCatching { db.readableDatabase.rawQuery(
            "SELECT * FROM payment WHERE month like ${month};",null
        ) }

    override fun insertHistory(historyItem: HistoryItem): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun updateHistory(historyItem: HistoryItem): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteHistory(historyItem: HistoryItem): Result<Unit> {
        TODO("Not yet implemented")
    }

    /**
     * Category
     */

    override fun getAllCategory(): Result<Cursor> =
        runCatching { db.readableDatabase.rawQuery(
            "SELECT * FROM category;",null
        ) }

    override fun getCategory(id: Int): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM category WHERE id like ${id};", null
            )
        }

    override fun insertCategory(category: Category): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun updateCategory(category: Category): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteCategory(category: Category): Result<Unit> {
        TODO("Not yet implemented")
    }

    /**
     * PaymentMethod
     */

    override fun getAllPaymentMethod(): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM payment_method;", null
            )
        }

    override fun getPaymentMethod(id: Int): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM payment_method WHERE id like ${id};", null
            )
        }

    override fun insertPaymentMethod(paymentMethod: PaymentMethod): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun updatePaymentMethod(paymentMethod: PaymentMethod): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun deletePaymentMethod(paymentMethod: PaymentMethod): Result<Unit> {
        TODO("Not yet implemented")
    }

}