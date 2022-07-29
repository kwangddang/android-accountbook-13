
package com.example.android_accountbook_13.data.local.repository

import android.database.Cursor
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val localDataSourceImpl: LocalDataSourceImpl
) : LocalRepository {
    override fun getHistory(month: Int): Cursor =
        localDataSourceImpl.getHistory(month)

    override fun getAllCategory(): Cursor =
        localDataSourceImpl.getAllCategory()

    override fun getCategory(id: Int): Cursor =
        localDataSourceImpl.getCategory(id)

    override fun getAllPaymentMethod(): Cursor =
        localDataSourceImpl.getAllPaymentMethod()

    override fun getPaymentMethod(id: Int): Cursor =
        localDataSourceImpl.getPaymentMethod(id)
}