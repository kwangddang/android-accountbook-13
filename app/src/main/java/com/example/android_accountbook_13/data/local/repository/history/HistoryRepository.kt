package com.example.android_accountbook_13.data.local.repository.history

import android.database.Cursor
import com.example.android_accountbook_13.presenter.History

interface HistoryRepository {
    fun getHistory(month: Int): Result<Cursor>
    fun insertHistory(history: History): Result<Unit>
    fun updateHistory(history: History): Result<Unit>
    fun deleteHistory(history: History): Result<Unit>
}