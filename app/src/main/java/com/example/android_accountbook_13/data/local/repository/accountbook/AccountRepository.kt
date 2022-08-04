package com.example.android_accountbook_13.data.local.repository.accountbook

import com.example.android_accountbook_13.data.dto.AccountBookItem

interface AccountRepository {
    suspend fun getAccountBook(year: Int, month: Int, onFailure: () -> Unit, onSuccess: (List<AccountBookItem>) -> Unit)
}