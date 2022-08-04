package com.example.android_accountbook_13.data.local.repository.method

import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.Method

interface MethodRepository {
    suspend fun getAllMethod(): DataResponse<List<Method>>
    suspend fun getMethod(id: Int): DataResponse<Method>
    suspend fun insertMethod(method : Method): DataResponse<Unit>
    suspend fun updateMethod(method : Method): DataResponse<Unit>
    suspend fun deleteMethod(methodId: Int): DataResponse<Unit>
}