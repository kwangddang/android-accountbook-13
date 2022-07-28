
package com.example.android_accountbook_13.data.local

import android.database.Cursor
import com.example.android_accountbook_13.domain.LocalRepository

class LocalRepositoryImpl(
    private val localDataSource: LocalDataSource
) : LocalRepository {
    override fun getHistory(month: Int): Cursor =
        localDataSource.getHistory(month)

    override fun getAllCategory(): Cursor =
        localDataSource.getAllCategory()

    override fun getCategory(id: Int): Cursor =
        localDataSource.getCategory(id)

    override fun getAllPaymentMethod(): Cursor =
        localDataSource.getAllPaymentMethod()

    override fun getPaymentMethod(id: Int): Cursor =
        localDataSource.getPaymentMethod(id)
}