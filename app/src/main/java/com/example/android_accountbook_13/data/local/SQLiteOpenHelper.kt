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
                    "color TEXT," +
                    "type INTEGER)"
        )
        db?.execSQL(
            "CREATE TABLE method(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT)"
        )
        db?.execSQL(
            "INSERT INTO category ('name','color','type') VALUES " +
                    "('미분류','#817DCE','1')," +
                    "('미분류','#817DCE','0')," +
                    "('교통','#94D3CC','1')," +
                    "('문화/여가','#D092E2','1')," +
                    "('생활','#4A6CC3','1')," +
                    "('쇼핑/뷰티','#4CB8B8','1')," +
                    "('식비','#4CA1DE','1')," +
                    "('의료/건강','#6ED5EB','1')," +
                    "('월급','#9BD182','0')," +
                    "('용돈','#EDCF65','0')," +
                    "('기타','#E29C4D','0');"
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