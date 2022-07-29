package com.example.android_accountbook_13.di

import com.example.android_accountbook_13.data.local.datasource.LocalDataSource
import com.example.android_accountbook_13.data.local.datasource.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract
class DataSourceModule {
    @Binds
    abstract fun provideLocalDataSource(
        localDateSourceImpl: LocalDataSourceImpl
    ): LocalDataSource
}