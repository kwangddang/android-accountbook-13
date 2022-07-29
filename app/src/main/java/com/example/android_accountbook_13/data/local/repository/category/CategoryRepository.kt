package com.example.android_accountbook_13.data.local.repository.category

import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.Category

interface CategoryRepository {
    fun getAllCategory(): DataResponse<List<Category>>
    fun getCategory(id: Int): DataResponse<Category>
    fun insertCategory(category: Category): Result<Unit>
    fun updateCategory(category: Category): Result<Unit>
    fun deleteCategory(category: Category): Result<Unit>
}