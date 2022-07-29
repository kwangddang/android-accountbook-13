package com.example.android_accountbook_13.data.local.repository.history

import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import com.example.android_accountbook_13.utils.getHistoryFromCursor
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
private val localDataSource: LocalDataSourceImpl
) : HistoryRepository {
    override fun getHistory(month: Int): DataResponse<List<History>> {
        val cursor = localDataSource.getHistory(month).getOrNull() ?: return DataResponse.Error()
        val itemList = mutableListOf<History>()
        while (cursor.moveToNext()) {
            itemList.add(getHistoryFromCursor(cursor,0))
        }
        return DataResponse.Success(itemList)
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