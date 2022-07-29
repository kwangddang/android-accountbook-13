package com.example.android_accountbook_13.data.local.repository.method

import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.Method

interface MethodRepository {
    fun getAllMethod(): DataResponse<List<Method>>
    fun getMethod(id: Int): DataResponse<Method>
    fun insertMethod(method : Method): DataResponse<Unit>
    fun updateMethod(method : Method): DataResponse<Unit>
    fun deleteMethod(methodId: Int): DataResponse<Unit>
}