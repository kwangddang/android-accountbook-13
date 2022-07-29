package com.example.android_accountbook_13.data.local.repository.method

import android.database.Cursor
import com.example.android_accountbook_13.data.entity.Method

interface MethodRepository {
    fun getAllMethod(): Result<Cursor>
    fun getMethod(id: Int): Result<Cursor>
    fun insertMethod(method : Method): Result<Unit>
    fun updateMethod(method : Method): Result<Unit>
    fun deleteMethod(method : Method): Result<Unit>
}