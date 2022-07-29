package com.example.android_accountbook_13.data.local

import android.database.Cursor

class LocalDataSource (
    private val db: SQLiteOpenHelper
) {
    fun getHistory(month: Int): Cursor =
        db.readableDatabase.rawQuery(
            "SELECT * FROM payment WHERE month like ${month};",null
        )

    fun getAllCategory(): Cursor =
        db.readableDatabase.rawQuery(
            "SELECT * FROM category;",null
        )

    fun getCategory(id: Int): Cursor =
        db.readableDatabase.rawQuery(
            "SELECT * FROM category WHERE id like ${id};",null
        )

    fun getAllPaymentMethod(): Cursor =
        db.readableDatabase.rawQuery(
            "SELECT * FROM payment_method;",null
        )

    fun getPaymentMethod(id: Int): Cursor =
        db.readableDatabase.rawQuery(
            "SELECT * FROM payment_method WHERE id like ${id};",null
        )

}