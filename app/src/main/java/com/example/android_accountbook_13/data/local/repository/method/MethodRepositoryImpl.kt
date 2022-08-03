package com.example.android_accountbook_13.data.local.repository.method

import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import com.example.android_accountbook_13.utils.getMethodFromCursor
import javax.inject.Inject

class MethodRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl
) : MethodRepository {
    override fun getAllMethod(): DataResponse<List<Method>> {
        val cursor = localDataSource.getAllMethod().getOrNull() ?: return DataResponse.Error("결제 수단을 불러오지 못 했습니다.")
        val itemList = mutableListOf<Method>()
        while (cursor.moveToNext()) {
            itemList.add(getMethodFromCursor(cursor,0))
        }
        return DataResponse.Success(itemList)
    }

    override fun getMethod(id: Int): DataResponse<Method> {
        val cursor = localDataSource.getMethod(id).getOrNull() ?: return DataResponse.Error("결제 수단을 불러오지 못 했습니다.")
        cursor.moveToNext()
        return DataResponse.Success(getMethodFromCursor(cursor,0))
    }

    override fun insertMethod(method : Method): DataResponse<Unit> {
        if(localDataSource.insertMethod(method).getOrNull() == null) return DataResponse.Error("결제 수단을 추가하지 못 했습니다.")
        return DataResponse.Success(Unit)
    }

    override fun updateMethod(method : Method): DataResponse<Unit> {
        if(localDataSource.updateMethod(method).getOrNull() == null) return DataResponse.Error("결제 수단을 수정하지 못 했습니다.")
        return DataResponse.Success(Unit)
    }

    override fun deleteMethod(methodId: Int): DataResponse<Unit> {
        if(localDataSource.deleteMethod(methodId).getOrNull() == null) return DataResponse.Error("결제 수단을 삭제하지 못 했습니다.")
        return DataResponse.Success(Unit)
    }
}