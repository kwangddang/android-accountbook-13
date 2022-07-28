package com.example.android_accountbook_13.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteOpenHelper
constructor(
    private val context: Context
) : SQLiteOpenHelper(context, DB_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE payment(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "categoryId INTEGER," +
                    "paymentMethodId INTEGER," +
                    "name TEXT," +
                    "methodType INTEGER," +
                    "money INTEGER," +
                    "year INTEGER," +
                    "month INTEGER," +
                    "day INTEGER)"
        )
        db?.execSQL(
            "CREATE TABLE category(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "color TEXT)"
        )
        db?.execSQL(
            "CREATE TABLE payment_method(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS payment")
        db?.execSQL("DROP TABLE IF EXISTS category")
        db?.execSQL("DROP TABLE IF EXISTS payment_method")
        onCreate(db)
    }

    companion object {
        const val DB_NAME = "AccountBook"
    }
}