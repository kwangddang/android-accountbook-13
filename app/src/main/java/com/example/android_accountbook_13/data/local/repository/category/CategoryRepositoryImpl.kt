
package com.example.android_accountbook_13.data.local.repository.category

import android.database.Cursor
import com.example.android_accountbook_13.data.entity.Category
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl
) : CategoryRepository {
    override fun getAllCategory(): Result<Cursor> {
        TODO("Not yet implemented")
    }

    override fun getCategory(id: Int): Result<Cursor> {
        TODO("Not yet implemented")
    }

    override fun insertCategory(category: Category): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun updateCategory(category: Category): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteCategory(category: Category): Result<Unit> {
        TODO("Not yet implemented")
    }

}