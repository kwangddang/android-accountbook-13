package com.example.android_accountbook_13.data.local.datasource

import android.database.Cursor
import android.util.Log
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.data.local.SQLiteOpenHelper
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val db: SQLiteOpenHelper
) : LocalDataSource {
    /**
     * History
     */
    override fun getHistory(year: Int, month: Int): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM history WHERE year = $year AND month = ${month};", null
            )
        }

    override fun insertHistory(history: History): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "INSERT INTO history ('category_id','method_id','name','method_type','money','year','month','day')" +
                        "VALUES (" +
                        "${history.categoryId},${history.methodId}," +
                        "'${history.name}',${history.methodType}," +
                        "${history.money},${history.year}," +
                        "${history.month},${history.day}) "
            )
        }


    override fun updateHistory(history: History): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "UPDATE history SET " +
                        "category_id = ${history.categoryId}, method_id = ${history.methodId}," +
                        "name = ${history.name}, method_type = ${history.methodType}," +
                        "money = ${history.money}, year = ${history.year}," +
                        "month = ${history.month}, day = ${history.day} " +
                        "WHERE id = ${history.id};"
            )
        }

    override fun deleteHistory(historyId: Int): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "DELETE FROM history WHERE id = ${historyId};"
            )
        }


    /**
     * Category
     */

    override fun getAllCategory(): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM category;", null
            )
        }

    override fun getCategory(id: Int): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM category WHERE id like ${id};", null
            )
        }

    override fun insertCategory(category: Category): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "INSERT INTO category ('name','color') VALUES ('${category.name}','${category.color}')"
            )
        }

    override fun updateCategory(category: Category): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "UPDATE category SET name = ${category.name}, color = ${category.color} WHERE id = ${category.id};"
            )
        }

    override fun deleteCategory(categoryId: Int): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "DELETE FROM category WHERE id = ${categoryId};"
            )
        }

    /**
     * Method
     */

    override fun getAllMethod(): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM method;", null
            )
        }

    override fun getMethod(id: Int): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM method WHERE id like ${id};", null
            )
        }

    override fun insertMethod(method: Method): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "INSERT INTO method ('name') VALUES ('${method.name}')"
            )
        }

    override fun updateMethod(method: Method): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "UPDATE method SET name = ${method.name} WHERE id = ${method.id};"
            )
        }

    override fun deleteMethod(methodId: Int): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "DELETE FROM method WHERE id = ${methodId};"
            )
        }

    /**
     *  AccountBook
     */
    override fun getAccountBook(year: Int, month: Int): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM history h INNER JOIN category c ON h.category_id = c.id INNER JOIN method m ON h.method_id = m.id WHERE h.month = ${month} AND h.year = ${year};", null
            )
        }
}