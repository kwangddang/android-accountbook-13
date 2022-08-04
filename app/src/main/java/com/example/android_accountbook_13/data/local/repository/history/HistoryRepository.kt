package com.example.android_accountbook_13.data.local.repository.history

import com.example.android_accountbook_13.data.dto.History

interface HistoryRepository {
    suspend fun insertHistory(history: History, onFailure: () -> Unit, onSuccess: () -> Unit)
    suspend fun updateHistory(history: History, onFailure: () -> Unit, onSuccess: () -> Unit)
    suspend fun deleteHistory(historyIds: List<Int>, onFailure: () -> Unit, onSuccess: () -> Unit)
}