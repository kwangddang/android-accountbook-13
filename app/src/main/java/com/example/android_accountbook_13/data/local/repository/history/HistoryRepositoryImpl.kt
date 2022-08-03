package com.example.android_accountbook_13.data.local.repository.history

import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import com.example.android_accountbook_13.utils.getHistoryFromCursor
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
private val localDataSource: LocalDataSourceImpl
) : HistoryRepository {
    override fun getHistory(year: Int, month: Int): DataResponse<List<History>> {
        val cursor = localDataSource.getHistory(year, month).getOrNull() ?: return DataResponse.Error("내역을 불러오지 못 했습니다.")
        val itemList = mutableListOf<History>()
        while (cursor.moveToNext()) {
            itemList.add(getHistoryFromCursor(cursor,0))
        }
        return DataResponse.Success(itemList)
    }

    override fun insertHistory(history: History): DataResponse<Unit> {
        if(localDataSource.insertHistory(history).getOrNull() == null) return DataResponse.Error("내역을 추가하지 못 했습니다.")
        return DataResponse.Success(Unit)
    }

    override fun updateHistory(history: History): DataResponse<Unit> {
        if(localDataSource.updateHistory(history).getOrNull() == null) return DataResponse.Error("내역을 수정하지 못 했습니다.")
        return DataResponse.Success(Unit)
    }

    override fun deleteHistory(historyIds: List<Int>): DataResponse<Unit> {
        if(localDataSource.deleteHistory(historyIds).getOrNull() == null) return DataResponse.Error("내역을 삭제하지 못 했습니다.")
        return DataResponse.Success(Unit)
    }

}