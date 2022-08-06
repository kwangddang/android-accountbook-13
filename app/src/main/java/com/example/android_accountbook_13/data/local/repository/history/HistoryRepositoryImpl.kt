package com.example.android_accountbook_13.data.local.repository.history

import android.util.Log
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
private val localDataSource: LocalDataSourceImpl
) : HistoryRepository {

    override suspend fun insertHistory(history: History, onFailure: (String?) -> Unit, onSuccess: () -> Unit) {
        localDataSource.insertHistory(history).onSuccess {
            onSuccess()
        }.onFailure {
            onFailure(it.message)
        }
    }

    override suspend fun updateHistory(history: History, onFailure: (String?) -> Unit, onSuccess: () -> Unit) {
        localDataSource.updateHistory(history).onSuccess {
            onSuccess()
        }.onFailure {
            onFailure(it.message)
        }
    }

    override suspend fun deleteHistory(historyIds: List<Int>, onFailure: (String?) -> Unit, onSuccess: () -> Unit) {
        localDataSource.deleteHistory(historyIds).onSuccess {
            onSuccess()
        }.onFailure {
            onFailure(it.message )
        }
    }

}