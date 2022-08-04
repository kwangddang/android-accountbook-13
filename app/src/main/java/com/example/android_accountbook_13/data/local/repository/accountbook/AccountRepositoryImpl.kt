package com.example.android_accountbook_13.data.local.repository.accountbook

import com.example.android_accountbook_13.data.dto.AccountBookItem
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import com.example.android_accountbook_13.utils.getCategoryFromCursor
import com.example.android_accountbook_13.utils.getHistoryFromCursor
import com.example.android_accountbook_13.utils.getMethodFromCursor
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl
) : AccountRepository {
    override suspend fun getAccountBook(year: Int, month: Int, onFailure: () -> Unit, onSuccess: (List<AccountBookItem>) -> Unit) {
        localDataSource.getAccountBook(year, month).onSuccess { cursor ->
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
            onSuccess(itemList)
        }.onFailure {
            onFailure()
        }
    }
}