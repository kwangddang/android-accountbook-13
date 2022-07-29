package com.example.android_accountbook_13.di

import com.example.android_accountbook_13.data.local.repository.LocalRepositoryImpl
import com.example.android_accountbook_13.data.local.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract
class RepositoryModule {
    @Binds
    abstract fun provideLocalRepository(
        repositoryImpl: LocalRepositoryImpl
    ): LocalRepository
}