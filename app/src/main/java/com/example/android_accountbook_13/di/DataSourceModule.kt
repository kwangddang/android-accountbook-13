package com.example.android_accountbook_13.di

import com.example.android_accountbook_13.data.local.repository.LocalRepository
import com.example.android_accountbook_13.data.local.repository.LocalRepositoryImpl
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
        localRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository
}