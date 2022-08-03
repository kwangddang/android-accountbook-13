package com.example.android_accountbook_13.data.local.repository.accountbook

import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.AccountBookItem
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import com.example.android_accountbook_13.utils.getCategoryFromCursor
import com.example.android_accountbook_13.utils.getHistoryFromCursor
import com.example.android_accountbook_13.utils.getMethodFromCursor
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl
) : AccountRepository {
    override fun getAccountBook(year: Int, month: Int): DataResponse<List<AccountBookItem>> {
        val cursor = localDataSource.getAccountBook(year, month).getOrNull() ?: return DataResponse.Error("내역을 불러오지 못 했습니다.")
        val itemList = mutableListOf<AccountBookItem>()
        while (cursor.moveToNext()) {
            itemList.add(
                AccountBookItem(
                    getHistoryFromCursor(cursor, 0),
                    getCategoryFromCursor(cursor, 9),
                    getMethodFromCursor(cursor, 13)
                )
            )
        }
        return DataResponse.Success(itemList)
    }
}