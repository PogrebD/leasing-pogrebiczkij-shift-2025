package com.pogreb.leasingshift.feature.carslist.di

import com.pogreb.leasingshift.feature.carslist.data.CarsListApi
import com.pogreb.leasingshift.feature.carslist.data.converter.CarsItemConverter
import com.pogreb.leasingshift.feature.carslist.data.repository.CarsListRepositoryImpl
import com.pogreb.leasingshift.feature.carslist.domain.repository.CarsListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
interface CarsListModule {
    companion object {
        @Provides
        fun provideCarsApi(retrofit: Retrofit): CarsListApi {
            return retrofit.create(CarsListApi::class.java)
        }

        @Provides
        fun provideCarsItemConverter(): CarsItemConverter {
            return CarsItemConverter()
        }
    }
    @Binds
    fun bindCarsRepository(impl: CarsListRepositoryImpl): CarsListRepository
}