package com.example.android_accountbook_13.data.local.datasource

import android.database.Cursor
import com.example.android_accountbook_13.data.local.SQLiteOpenHelper

class LocalDataSourceImpl(
    private val db: SQLiteOpenHelper
): LocalDataSource {
    override fun getHistory(month: Int): Cursor =
        db.readableDatabase.rawQuery(
            "SELECT * FROM payment WHERE month like ${month};",null
        )

    override fun getAllCategory(): Cursor =
        db.readableDatabase.rawQuery(
            "SELECT * FROM category;",null
        )

    override fun getCategory(id: Int): Cursor =
        db.readableDatabase.rawQuery(
            "SELECT * FROM category WHERE id like ${id};",null
        )

    override fun getAllPaymentMethod(): Cursor =
        db.readableDatabase.rawQuery(
            "SELECT * FROM payment_method;",null
        )

    override fun getPaymentMethod(id: Int): Cursor =
        db.readableDatabase.rawQuery(
            "SELECT * FROM payment_method WHERE id like ${id};",null
        )

}