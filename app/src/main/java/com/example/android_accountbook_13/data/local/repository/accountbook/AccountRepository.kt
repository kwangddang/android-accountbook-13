package com.example.android_accountbook_13.data.local.repository.accountbook

import android.database.Cursor
import com.example.android_accountbook_13.data.dto.Category

interface AccountRepository {
    fun getAccountBook(): Result<Cursor>
}