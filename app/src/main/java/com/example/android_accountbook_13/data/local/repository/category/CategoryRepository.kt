package com.example.android_accountbook_13.data.local.repository.category

import com.example.android_accountbook_13.data.dto.Category

interface CategoryRepository {
    suspend fun getIncomeCategory(onFailure: () -> Unit, onSuccess: (List<Category>) -> Unit)
    suspend fun getExpenseCategory(onFailure: () -> Unit, onSuccess: (List<Category>) -> Unit)
    suspend fun insertCategory(category: Category, onFailure: () -> Unit, onSuccess: () -> Unit)
    suspend fun updateCategory(category: Category, onFailure: () -> Unit, onSuccess: () -> Unit)
}