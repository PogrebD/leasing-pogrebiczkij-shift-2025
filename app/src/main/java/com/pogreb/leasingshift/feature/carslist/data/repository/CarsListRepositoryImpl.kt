package com.pogreb.leasingshift.feature.carslist.data.repository

import com.pogreb.leasingshift.feature.carslist.data.CarsListApi
import com.pogreb.leasingshift.feature.carslist.data.converter.CarsItemConverter
import com.pogreb.leasingshift.feature.carslist.domain.entity.CarsItem
import com.pogreb.leasingshift.feature.carslist.domain.repository.CarsListRepository
import javax.inject.Inject

class CarsListRepositoryImpl @Inject constructor(
    private val api: CarsListApi,
    private val converter: CarsItemConverter,
) : CarsListRepository {
    override suspend fun getAllCars(): List<CarsItem> =
        api.getAllCars().data.map { converter.convert(it) }
}