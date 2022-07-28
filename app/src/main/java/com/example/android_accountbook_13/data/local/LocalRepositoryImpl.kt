package com.example.android_accountbook_13.data.local

import android.database.Cursor
import com.example.android_accountbook_13.domain.LocalRepository

class LocalRepositoryImpl(
    private val localDataSource: LocalDataSource
) : LocalRepository {
    override fun getHistory(month: Int): Cursor =
        localDataSource.getHistory(month)
}