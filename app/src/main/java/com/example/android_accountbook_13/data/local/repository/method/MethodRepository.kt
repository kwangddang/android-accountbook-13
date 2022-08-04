package com.example.android_accountbook_13.data.local.repository.method

import com.example.android_accountbook_13.data.dto.Method

interface MethodRepository {
    suspend fun getAllMethod(onFailure: (String?) -> Unit, onSuccess: (List<Method>) -> Unit)
    suspend fun insertMethod(method : Method, onFailure: (String?) -> Unit, onSuccess: () -> Unit)
    suspend fun updateMethod(method : Method, onFailure: (String?) -> Unit, onSuccess: () -> Unit)
}