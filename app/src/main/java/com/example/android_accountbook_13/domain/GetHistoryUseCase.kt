package com.example.android_accountbook_13.domain

import com.example.android_accountbook_13.data.HistoryItem
import com.example.android_accountbook_13.data.entity.Category
import com.example.android_accountbook_13.data.entity.Payment
import com.example.android_accountbook_13.data.entity.PaymentMethod

class GetHistoryUseCase(
    private val localRepository: LocalRepository
) {
    operator fun invoke(month: Int): List<HistoryItem> {
        val historyCursor = localRepository.getHistory(month)
        var paymentList = mutableListOf<Payment>()
        historyCursor.run {
            while (this.moveToNext()) {
                val id = this.getInt(0)
                val categoryId = this.getInt(1)
                val paymentMethodId = this.getInt(2)
                val name = this.getString(3)
                val methodType = this.getInt(4)
                val money = this.getInt(5)
                val year = this.getInt(6)
                val month = this.getInt(7)
                val day = this.getInt(8)
                paymentList.add(Payment(id, categoryId, paymentMethodId, name, methodType, money, year, month, day))
            }
        }

        var categoryList = mutableListOf<Category>()
        var paymentMethodList = mutableListOf<PaymentMethod>()

        paymentList.forEach { payment ->
            val categoryCursor = localRepository.getCategory(payment.categoryId)
            categoryCursor.run {
                while (this.moveToNext()) {
                    val id = this.getInt(0)
                    val name = this.getString(1)
                    val color = this.getString(2)
                    categoryList.add(Category(id, name, color))
                }
            }
            val paymentMethodCursor = localRepository.getPaymentMethod(payment.paymentMethodId)
            paymentMethodCursor.run {
                while (this.moveToNext()) {
                    val id = this.getInt(0)
                    val name = this.getString(1)
                    paymentMethodList.add(PaymentMethod(id, name))
                }
            }
        }
        val historyList = mutableListOf<HistoryItem>()
        for(i in 0..paymentList.size) {
            historyList.add(HistoryItem(paymentList[i], categoryList[i], paymentMethodList[i]))
        }
        return historyList
    }
}