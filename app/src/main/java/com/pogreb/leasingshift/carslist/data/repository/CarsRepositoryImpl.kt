package com.pogreb.leasingshift.carslist.data.repository

import com.pogreb.leasingshift.carslist.data.CarsApi
import com.pogreb.leasingshift.carslist.data.converter.CarsItemConverter
import com.pogreb.leasingshift.carslist.domain.entity.CarsItem
import com.pogreb.leasingshift.carslist.domain.repository.CarRepository

class CarsRepositoryImpl(
    private val api: CarsApi,
    private val converter: CarsItemConverter,
) : CarRepository {
    override suspend fun getAllCars(): List<CarsItem> =
        api.getAllCars().data.map { converter.convert(it) }

}