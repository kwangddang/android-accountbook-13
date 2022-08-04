package com.example.android_accountbook_13.data.local.repository.method

import com.example.android_accountbook_13.data.dto.Method

interface MethodRepository {
    suspend fun getAllMethod(onFailure: () -> Unit, onSuccess: (List<Method>) -> Unit)
    suspend fun insertMethod(method : Method, onFailure: () -> Unit, onSuccess: () -> Unit)
    suspend fun updateMethod(method : Method, onFailure: () -> Unit, onSuccess: () -> Unit)
}