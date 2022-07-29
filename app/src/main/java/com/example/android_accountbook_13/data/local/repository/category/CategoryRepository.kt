package com.example.android_accountbook_13.data.local.repository.category

import android.database.Cursor
import com.example.android_accountbook_13.data.entity.Category

interface CategoryRepository {
    fun getAllCategory(): Result<Cursor>
    fun getCategory(id: Int): Result<Cursor>
    fun insertCategory(category: Category): Result<Unit>
    fun updateCategory(category: Category): Result<Unit>
    fun deleteCategory(category: Category): Result<Unit>
}