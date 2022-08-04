package com.example.android_accountbook_13.data.local.repository.history

import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.History

interface HistoryRepository {
    suspend fun insertHistory(history: History): DataResponse<Unit>
    suspend fun updateHistory(history: History): DataResponse<Unit>
    suspend fun deleteHistory(historyIds: List<Int>): DataResponse<Unit>
}