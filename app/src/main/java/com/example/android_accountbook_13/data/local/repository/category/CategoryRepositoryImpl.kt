
package com.example.android_accountbook_13.data.local.repository.category

import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import com.example.android_accountbook_13.utils.getCategoryFromCursor
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl
) : CategoryRepository {
    override fun getAllCategory(): DataResponse<List<Category>> {
        val cursor = localDataSource.getAllCategory().getOrNull() ?: return DataResponse.Error()
        val itemList = mutableListOf<Category>()
        while (cursor.moveToNext()) {
            itemList.add(getCategoryFromCursor(cursor,0))
        }
        return DataResponse.Success(itemList)
    }

    override fun getCategory(id: Int): DataResponse<Category> {
        val cursor = localDataSource.getCategory(id).getOrNull() ?: return DataResponse.Error()
        cursor.moveToNext()
        return DataResponse.Success(getCategoryFromCursor(cursor,0))
    }

    override fun insertCategory(category: Category): DataResponse<Unit> {
        if(localDataSource.insertCategory(category).getOrNull() == null)
            return DataResponse.Error()
        return DataResponse.Success(Unit)
    }

    override fun updateCategory(category: Category): DataResponse<Unit> {
        if(localDataSource.updateCategory(category).getOrNull() == null)
            return DataResponse.Error()
        return DataResponse.Success(Unit)
    }

    override fun deleteCategory(categoryId: Int): DataResponse<Unit> {
        if(localDataSource.deleteCategory(categoryId).getOrNull() == null)
            return DataResponse.Error()
        return DataResponse.Success(Unit)
    }

}