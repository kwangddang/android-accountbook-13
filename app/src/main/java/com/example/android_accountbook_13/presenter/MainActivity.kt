package com.example.android_accountbook_13.presenter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.android_accountbook_13.data.entity.Payment
import com.example.android_accountbook_13.data.local.SQLiteOpenHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccountBookApp()
        }
        CoroutineScope(Dispatchers.IO).launch {
            val cursor = SQLiteOpenHelper(applicationContext).readableDatabase.rawQuery(
                "SELECT * FROM payment;",null
            )
            while(cursor.moveToNext()) {
                val id = cursor.getInt(0)
                val categoryId = cursor.getInt(1)
                val paymentMethodId = cursor.getInt(2)
                val name = cursor.getString(3)
                val methodType = cursor.getInt(4)
                val money = cursor.getInt(5)
                val year = cursor.getInt(6)
                val month = cursor.getInt(7)
                val day = cursor.getInt(8)
                val payment = Payment(
                    id,categoryId,paymentMethodId,name,methodType,money,year,month,day)
            }
        }
    }
}