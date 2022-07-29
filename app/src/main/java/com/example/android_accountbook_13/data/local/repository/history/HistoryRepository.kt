package com.example.android_accountbook_13.data.local.repository.history

import android.database.Cursor
import com.example.android_accountbook_13.data.HistoryItem

interface HistoryRepository {
    fun getHistory(month: Int): Result<Cursor>
    fun insertHistory(historyItem: HistoryItem): Result<Unit>
    fun updateHistory(historyItem: HistoryItem): Result<Unit>
    fun deleteHistory(historyItem: HistoryItem): Result<Unit>
}