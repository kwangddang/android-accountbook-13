package com.example.android_accountbook_13.data.local.datasource

import android.database.Cursor
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.dto.Method
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

    override fun insertHistory(history: History): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun updateHistory(history: History): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteHistory(history: History): Result<Unit> {
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
     * Method
     */

    override fun getAllMethod(): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM payment_method;", null
            )
        }

    override fun getMethod(id: Int): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM payment_method WHERE id like ${id};", null
            )
        }

    override fun insertMethod(method: Method): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun updateMethod(method: Method): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteMethod(method: Method): Result<Unit> {
        TODO("Not yet implemented")
    }

}