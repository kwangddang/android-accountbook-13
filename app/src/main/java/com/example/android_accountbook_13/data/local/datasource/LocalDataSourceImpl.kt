package com.example.android_accountbook_13.data.local.datasource

import android.database.Cursor
import android.text.TextUtils
import android.util.Log
import androidx.compose.ui.unit.TextUnit
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
    override suspend fun insertHistory(history: History): Result<Unit> =
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


    override suspend fun updateHistory(history: History): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "UPDATE history SET " +
                        "category_id = '${history.categoryId}', method_id = '${history.methodId}'," +
                        "name = '${history.name}', method_type = '${history.methodType}'," +
                        "money = '${history.money}', year = '${history.year}'," +
                        "month = '${history.month}', day = '${history.day}' " +
                        "WHERE id = ${history.id};"
            )
        }

    override suspend fun deleteHistory(historyIds: List<Int>): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "DELETE FROM history WHERE id IN (${TextUtils.join(",",historyIds)});"
            )
        }


    /**
     * Category
     */

    override suspend fun getIncomeCategory(): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM category WHERE type = 0;", null
            )
        }

    override suspend fun getExpenseCategory(): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM category WHERE type = 1;", null
            )
        }

    override suspend fun insertCategory(category: Category): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "INSERT INTO category ('name','color','type') VALUES ('${category.name}','${category.color}','${category.type}')"
            )
        }

    override suspend fun updateCategory(category: Category): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "UPDATE category SET name = '${category.name}', color = '${category.color}' WHERE id = ${category.id};"
            )
        }


    /**
     * Method
     */

    override suspend fun getAllMethod(): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM method;", null
            )
        }

    override suspend fun insertMethod(method: Method): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "INSERT INTO method ('name') VALUES ('${method.name}')"
            )
        }

    override suspend fun updateMethod(method: Method): Result<Unit> =
        runCatching {
            db.writableDatabase.execSQL(
                "UPDATE method SET name = '${method.name}' WHERE id = ${method.id};"
            )
        }

    /**
     *  AccountBook
     */
    override suspend fun getAccountBook(year: Int, month: Int): Result<Cursor> =
        runCatching {
            db.readableDatabase.rawQuery(
                "SELECT * FROM history h INNER JOIN category c ON h.category_id = c.id INNER JOIN method m ON h.method_id = m.id WHERE h.month = ${month} AND h.year = ${year} ORDER By day;", null
            )
        }
}