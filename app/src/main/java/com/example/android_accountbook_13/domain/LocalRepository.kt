package com.example.android_accountbook_13.domain

import android.database.Cursor

interface LocalRepository {
    fun getHistory(month: Int): Cursor
    fun getAllCategory(): Cursor
    fun getCategory(id: Int): Cursor
    fun getAllPaymentMethod(): Cursor
    fun getPaymentMethod(id: Int): Cursor
}