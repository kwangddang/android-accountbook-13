package com.example.android_accountbook_13.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SQLiteOpenHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE history(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "category_id INTEGER," +
                    "method_id INTEGER," +
                    "name TEXT," +
                    "method_type INTEGER," +
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
            "CREATE TABLE method(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS history")
        db?.execSQL("DROP TABLE IF EXISTS category")
        db?.execSQL("DROP TABLE IF EXISTS method")
        onCreate(db)
    }

    companion object {
        const val DB_NAME = "AccountBook"
    }
}