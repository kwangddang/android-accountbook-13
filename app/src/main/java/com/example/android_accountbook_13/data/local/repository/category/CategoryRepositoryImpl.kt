
package com.example.android_accountbook_13.data.local.repository.category

import android.database.Cursor
import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import com.example.android_accountbook_13.utils.getCategoryFromCursor
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl
) : CategoryRepository {
    override suspend fun getAllCategory(): DataResponse<List<Category>> {
        val cursor = localDataSource.getAllCategory().getOrNull() ?: return DataResponse.Error("전체 카테고리를 불러오지 못 했습니다.")
        return cursorToCategory(cursor)
    }

    override suspend fun getIncomeCategory(): DataResponse<List<Category>> {
        val cursor = localDataSource.getIncomeCategory().getOrNull() ?: return DataResponse.Error("수입 카테고리를 불러오지 못 했습니다.")
        return cursorToCategory(cursor)
    }

    override suspend fun getExpenseCategory(): DataResponse<List<Category>> {
        val cursor = localDataSource.getExpenseCategory().getOrNull() ?: return DataResponse.Error("지출 카테고리를 불러오지 못 했습니다.")
        return cursorToCategory(cursor)
    }

    override suspend fun insertCategory(category: Category): DataResponse<Unit> {
        if(localDataSource.insertCategory(category).getOrNull() == null) return DataResponse.Error("카테고리를 추가하지 못 했습니다.")
        return DataResponse.Success(Unit)
    }

    override suspend fun updateCategory(category: Category): DataResponse<Unit> {
        if(localDataSource.updateCategory(category).getOrNull() == null) return DataResponse.Error("카테고리를 수정하지 못 했습니다.")
        return DataResponse.Success(Unit)
    }

    override suspend fun deleteCategory(categoryId: Int): DataResponse<Unit> {
        if(localDataSource.deleteCategory(categoryId).getOrNull() == null) return DataResponse.Error("카테고리를 삭제하지 못 했습니다.")
        return DataResponse.Success(Unit)
    }

    private suspend fun cursorToCategory(cursor: Cursor): DataResponse<List<Category>> {
        val itemList = mutableListOf<Category>()
        while (cursor.moveToNext()) {
            itemList.add(getCategoryFromCursor(cursor, 0))
        }
        return DataResponse.Success(itemList)
    }

}