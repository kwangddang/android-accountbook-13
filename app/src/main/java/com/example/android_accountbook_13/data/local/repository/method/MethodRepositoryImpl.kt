package com.example.android_accountbook_13.data.local.repository.method

import android.database.Cursor
import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import com.example.android_accountbook_13.utils.getHistoryFromCursor
import com.example.android_accountbook_13.utils.getMethodFromCursor
import javax.inject.Inject

class MethodRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl
) : MethodRepository {
    override fun getAllMethod(): DataResponse<List<Method>> {
        val cursor = localDataSource.getAllMethod().getOrNull() ?: return DataResponse.Error()
        val itemList = mutableListOf<Method>()
        while (cursor.moveToNext()) {
            itemList.add(getMethodFromCursor(cursor,0))
        }
        return DataResponse.Success(itemList)
    }

    override fun getMethod(id: Int): DataResponse<List<Method>> {
        TODO("Not yet implemented")
    }

    override fun insertMethod(method : Method): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun updateMethod(method : Method): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteMethod(method : Method): Result<Unit> {
        TODO("Not yet implemented")
    }
}