package com.example.android_accountbook_13.data.local.repository.history

import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
private val localDataSource: LocalDataSourceImpl
) : HistoryRepository {

    override suspend fun insertHistory(history: History, onFailure: () -> Unit, onSuccess: () -> Unit) {
        localDataSource.insertHistory(history).onSuccess {
            onSuccess()
        }.onFailure {
            onFailure()
        }
    }

    override suspend fun updateHistory(history: History, onFailure: () -> Unit, onSuccess: () -> Unit) {
        localDataSource.updateHistory(history).onSuccess {
            onSuccess()
        }.onFailure {
            onFailure()
        }
    }

    override suspend fun deleteHistory(historyIds: List<Int>, onFailure: () -> Unit, onSuccess: () -> Unit) {
        localDataSource.deleteHistory(historyIds).onSuccess {
            onSuccess()
        }.onFailure {
            onFailure()
        }
    }

}