package com.example.android_accountbook_13.presenter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.AccountBookItem
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.data.local.SQLiteOpenHelper
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class   MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccountBookApp()
        }
    }
}