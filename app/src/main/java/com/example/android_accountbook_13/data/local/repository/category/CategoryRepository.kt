package com.example.android_accountbook_13.data.local.repository.category

import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.Category

interface CategoryRepository {
    suspend fun getIncomeCategory(): DataResponse<List<Category>>
    suspend fun getExpenseCategory(): DataResponse<List<Category>>
    suspend fun insertCategory(category: Category): DataResponse<Unit>
    suspend fun updateCategory(category: Category): DataResponse<Unit>
}