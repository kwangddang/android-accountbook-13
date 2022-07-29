package com.example.android_accountbook_13.data.local.repository.method

import android.database.Cursor
import com.example.android_accountbook_13.data.entity.PaymentMethod

interface PaymentMethodRepository {
    fun getAllPaymentMethod(): Result<Cursor>
    fun getPaymentMethod(id: Int): Result<Cursor>
    fun insertPaymentMethod(paymentMethod: PaymentMethod): Result<Unit>
    fun updatePaymentMethod(paymentMethod: PaymentMethod): Result<Unit>
    fun deletePaymentMethod(paymentMethod: PaymentMethod): Result<Unit>
}