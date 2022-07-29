package com.example.android_accountbook_13.data.local.repository.history

import android.database.Cursor
import com.example.android_accountbook_13.data.HistoryItem
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
private val localDataSource: LocalDataSourceImpl
) : HistoryRepository {
    override fun getHistory(month: Int): Result<Cursor> {
        TODO("Not yet implemented")
    }

    override fun insertHistory(historyItem: HistoryItem): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun updateHistory(historyItem: HistoryItem): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteHistory(historyItem: HistoryItem): Result<Unit> {
        TODO("Not yet implemented")
    }
}