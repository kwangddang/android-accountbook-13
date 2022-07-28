package com.example.android_accountbook_13.di

import com.example.android_accountbook_13.domain.GetHistoryUseCase
import com.example.android_accountbook_13.domain.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Singleton
    @Provides
    fun providesLocalUseCase(
        repository: LocalRepository
    ): GetHistoryUseCase = GetHistoryUseCase(repository)
}