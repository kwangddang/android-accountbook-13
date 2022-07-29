package com.example.android_accountbook_13.data.local.repository.method

import android.database.Cursor
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import javax.inject.Inject

class MethodRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl
) : MethodRepository {
    override fun getAllMethod(): Result<Cursor> {
        TODO("Not yet implemented")
    }

    override fun getMethod(id: Int): Result<Cursor> {
        TODO("Not yet implemented")
    }

    override fun insertMethod(method : Method): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun updateMethod(method : Method): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteMethod(method : Method): Result<Unit> {
        TODO("Not yet implemented")
    }
}