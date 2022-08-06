package com.example.android_accountbook_13.data.local.repository.category

import com.example.android_accountbook_13.data.dto.Category

interface CategoryRepository {
    suspend fun getIncomeCategory(onFailure: (String?) -> Unit, onSuccess: (List<Category>) -> Unit)
    suspend fun getExpenseCategory(onFailure: (String?) -> Unit, onSuccess: (List<Category>) -> Unit)
    suspend fun insertCategory(category: Category, onFailure: (String?) -> Unit, onSuccess: () -> Unit)
    suspend fun updateCategory(category: Category, onFailure: (String?) -> Unit, onSuccess: () -> Unit)
}