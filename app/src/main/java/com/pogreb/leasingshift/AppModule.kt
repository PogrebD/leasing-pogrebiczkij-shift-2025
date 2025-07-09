package com.pogreb.leasingshift

import com.pogreb.leasingshift.carslist.di.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}