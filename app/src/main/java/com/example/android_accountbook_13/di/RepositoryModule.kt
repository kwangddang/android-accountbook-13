package com.example.android_accountbook_13.di

import com.example.android_accountbook_13.data.local.repository.accountbook.AccountRepository
import com.example.android_accountbook_13.data.local.repository.accountbook.AccountRepositoryImpl
import com.example.android_accountbook_13.data.local.repository.category.CategoryRepository
import com.example.android_accountbook_13.data.local.repository.category.CategoryRepositoryImpl
import com.example.android_accountbook_13.data.local.repository.history.HistoryRepository
import com.example.android_accountbook_13.data.local.repository.history.HistoryRepositoryImpl
import com.example.android_accountbook_13.data.local.repository.method.MethodRepository
import com.example.android_accountbook_13.data.local.repository.method.MethodRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract
class RepositoryModule {
    @Binds
    abstract fun provideHistoryRepository(
        historyRepositoryImpl: HistoryRepositoryImpl
    ): HistoryRepository

    @Binds
    abstract fun provideCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    abstract fun provideMethodRepository(
        methodRepositoryImpl: MethodRepositoryImpl
    ): MethodRepository

    @Binds
    abstract fun provideAccountRepository(
        accountRepositoryImpl: AccountRepositoryImpl
    ): AccountRepository
}