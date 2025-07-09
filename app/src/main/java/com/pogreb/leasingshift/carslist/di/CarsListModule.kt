package com.pogreb.leasingshift.carslist.di

import com.pogreb.leasingshift.carslist.data.CarsListApi
import com.pogreb.leasingshift.carslist.data.converter.CarsItemConverter
import com.pogreb.leasingshift.carslist.data.repository.CarsListRepositoryImpl
import com.pogreb.leasingshift.carslist.domain.repository.CarsListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CarsListModule {

    @Provides
    fun provideCarsApi(retrofit: Retrofit): CarsListApi {
        return retrofit.create(CarsListApi::class.java)
    }

    @Provides
    fun provideCarsItemConverter(): CarsItemConverter {
        return CarsItemConverter()
    }

    @Provides
    fun provideCarsRepository(
        api: CarsListApi,
        converter: CarsItemConverter
    ): CarsListRepository {
        return CarsListRepositoryImpl(api, converter)
    }
}