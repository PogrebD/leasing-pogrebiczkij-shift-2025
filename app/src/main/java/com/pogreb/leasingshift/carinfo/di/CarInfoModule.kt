package com.pogreb.leasingshift.carinfo.di

import com.pogreb.leasingshift.carinfo.data.CarInfoApi
import com.pogreb.leasingshift.carinfo.data.converter.CarInfoConverter
import com.pogreb.leasingshift.carinfo.data.repository.CarInfoRepositoryImpl
import com.pogreb.leasingshift.carinfo.domain.repository.CarInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CarInfoModule {

    @Provides
    fun provideCarInfoApi(retrofit: Retrofit): CarInfoApi {
        return retrofit.create(CarInfoApi::class.java)
    }

    @Provides
    fun provideCarInfoConverter(): CarInfoConverter {
        return CarInfoConverter()
    }

    @Provides
    fun provideCarInfoRepository(
        api: CarInfoApi,
        converter: CarInfoConverter
    ): CarInfoRepository {
        return CarInfoRepositoryImpl(api, converter)
    }
}