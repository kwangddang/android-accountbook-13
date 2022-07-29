package com.example.android_accountbook_13.data.local.repository.method

import android.database.Cursor
import com.example.android_accountbook_13.data.entity.PaymentMethod
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import javax.inject.Inject

class PaymentMethodRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl
) : PaymentMethodRepository {
    override fun getAllPaymentMethod(): Result<Cursor> {
        TODO("Not yet implemented")
    }

    override fun getPaymentMethod(id: Int): Result<Cursor> {
        TODO("Not yet implemented")
    }

    override fun insertPaymentMethod(paymentMethod: PaymentMethod): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun updatePaymentMethod(paymentMethod: PaymentMethod): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun deletePaymentMethod(paymentMethod: PaymentMethod): Result<Unit> {
        TODO("Not yet implemented")
    }
}