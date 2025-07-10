package com.pogreb.leasingshift.feature.carinfo.di

import com.pogreb.leasingshift.feature.carinfo.data.CarInfoApi
import com.pogreb.leasingshift.feature.carinfo.data.converter.CarInfoConverter
import com.pogreb.leasingshift.feature.carinfo.data.repository.CarInfoRepositoryImpl
import com.pogreb.leasingshift.feature.carinfo.domain.repository.CarInfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
interface CarInfoModule {

    companion object {

        @Provides
        fun provideCarInfoApi(retrofit: Retrofit): CarInfoApi {
            return retrofit.create(CarInfoApi::class.java)
        }

        @Provides
        fun provideCarInfoConverter(): CarInfoConverter {
            return CarInfoConverter()
        }
    }

    @Binds
    fun bindCarInfoRepository(impl: CarInfoRepositoryImpl): CarInfoRepository
}