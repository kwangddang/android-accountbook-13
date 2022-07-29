package com.example.android_accountbook_13.data.local.repository.accountbook

import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.AccountBookItem

interface AccountRepository {
    fun getAccountBook(month: Int): DataResponse<List<AccountBookItem>>
}