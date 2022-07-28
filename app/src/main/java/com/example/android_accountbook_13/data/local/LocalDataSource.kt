package com.example.android_accountbook_13.data.local

import android.database.Cursor

class LocalDataSource (
    private val db: SQLiteOpenHelper
) {
    fun getHistory(month: Int): Cursor {
        return db.readableDatabase.rawQuery(
            "SELECT * FROM payment WHERE month like ${month};",null
        )
    }
}