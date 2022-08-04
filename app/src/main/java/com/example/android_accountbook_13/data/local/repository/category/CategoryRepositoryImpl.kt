package com.example.android_accountbook_13.data.local.repository.category

import android.database.Cursor
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import com.example.android_accountbook_13.utils.getCategoryFromCursor
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl
) : CategoryRepository {

    override suspend fun getIncomeCategory(onFailure: () -> Unit, onSuccess: (List<Category>) -> Unit) {
        localDataSource.getIncomeCategory().onSuccess { cursor ->
            onSuccess(cursorToCategory(cursor))
        }.onFailure {
            onFailure()
        }
    }

    override suspend fun getExpenseCategory(onFailure: () -> Unit, onSuccess: (List<Category>) -> Unit) {
        localDataSource.getExpenseCategory().onSuccess { cursor ->
            onSuccess(cursorToCategory(cursor))
        }.onFailure {
            onFailure()
        }
    }

    override suspend fun insertCategory(category: Category, onFailure: () -> Unit, onSuccess: () -> Unit) {
        localDataSource.insertCategory(category).onSuccess {
            onSuccess()
        }.onFailure {
            onFailure()
        }
    }

    override suspend fun updateCategory(category: Category, onFailure: () -> Unit, onSuccess: () -> Unit) {
        localDataSource.updateCategory(category).onSuccess {
            onSuccess()
        }.onFailure {
            onFailure()
        }
    }

    private fun cursorToCategory(cursor: Cursor): List<Category> {
        val itemList = mutableListOf<Category>()
        while (cursor.moveToNext()) {
            itemList.add(getCategoryFromCursor(cursor, 0))
        }
        return itemList
    }

}