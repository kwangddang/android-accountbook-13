package com.example.android_accountbook_13.di

import android.content.Context
import com.example.android_accountbook_13.data.local.SQLiteOpenHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {
    @Singleton
    @Provides
    fun provideSQLiteOpenHelper(@ApplicationContext context: Context) : SQLiteOpenHelper =
        SQLiteOpenHelper(context)
}