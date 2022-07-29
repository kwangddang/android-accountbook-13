
package com.example.android_accountbook_13.data.local.repository.accountbook

import android.database.Cursor
import com.example.android_accountbook_13.data.dto.AccountBookItem
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl
) : AccountRepository {
    override fun getAccountBook(): Result<Cursor> {
        TODO("Not yet implemented")
//        val cursor = localDataSource.getAccountBook().getOrNull()
//        var item: AccountBookItem? = null
//        while (cursor.moveToNext()) {
//            val id1 = cursor.getInt(0)
//            val id2 = cursor.getInt(1)
//            val id3 = cursor.getInt(2)
//            val name = cursor.getString(3)
//            val methodType = cursor.getInt(4)
//            val money = cursor.getInt(5)
//            val year = cursor.getInt(6)
//            val month = cursor.getInt(7)
//            val day = cursor.getInt(8)
//            val categoryId = cursor.getInt(9)
//            val categoryName = cursor.getString(10)
//            val categoryColor = cursor.getString(11)
//            val methodId = cursor.getInt(12)
//            val methodName = cursor.getString(13)
//            item = AccountBookItem(
//                History(id1, id2, id3, name, methodType, money, year, month, day),
//                Category(categoryId, categoryName, categoryColor),
//                Method(methodId, methodName)
//            )
//        }
    }
}