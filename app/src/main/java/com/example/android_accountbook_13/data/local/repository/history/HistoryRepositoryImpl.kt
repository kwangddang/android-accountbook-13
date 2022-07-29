package com.example.android_accountbook_13.data.local.repository.history

import android.database.Cursor
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import com.example.android_accountbook_13.presenter.History
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
private val localDataSource: LocalDataSourceImpl
) : HistoryRepository {
    override fun getHistory(month: Int): Result<Cursor> {
        TODO("Not yet implemented")
    }

    override fun insertHistory(history: History): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun updateHistory(history: History): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteHistory(history: History): Result<Unit> {
        TODO("Not yet implemented")
    }

}