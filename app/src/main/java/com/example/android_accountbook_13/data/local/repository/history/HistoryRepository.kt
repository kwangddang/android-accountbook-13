package com.example.android_accountbook_13.data.local.repository.history

import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.History

interface HistoryRepository {
    fun getHistory(year: Int, month: Int): DataResponse<List<History>>
    fun insertHistory(history: History): DataResponse<Unit>
    fun updateHistory(history: History): DataResponse<Unit>
    fun deleteHistory(historyId: Int): DataResponse<Unit>
}