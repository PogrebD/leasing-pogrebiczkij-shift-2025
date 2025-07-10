package com.pogreb.leasingshift.shared.di

import com.pogreb.leasingshift.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}