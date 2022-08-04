package com.example.android_accountbook_13.data.local.repository.method

import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import com.example.android_accountbook_13.utils.getMethodFromCursor
import javax.inject.Inject

class MethodRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl
) : MethodRepository {
    override suspend fun getAllMethod(onFailure: () -> Unit, onSuccess: (List<Method>) -> Unit) {
        localDataSource.getAllMethod().onSuccess { cursor ->
            val itemList = mutableListOf<Method>()
            while (cursor.moveToNext()) {
                itemList.add(getMethodFromCursor(cursor,0))
            }
            onSuccess(itemList)
        }.onFailure {
            onFailure()
        }
    }

    override suspend fun insertMethod(method : Method, onFailure: () -> Unit, onSuccess: () -> Unit) {
        localDataSource.insertMethod(method).onSuccess {
            onSuccess()
        }.onFailure {
            onFailure()
        }
    }

    override suspend fun updateMethod(method : Method, onFailure: () -> Unit, onSuccess: () -> Unit) {
        localDataSource.updateMethod(method).onSuccess {
            onSuccess()
        }.onFailure {
            onFailure()
        }
    }
}