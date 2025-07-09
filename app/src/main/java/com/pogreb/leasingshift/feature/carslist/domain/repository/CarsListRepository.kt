package com.pogreb.leasingshift.feature.carslist.domain.repository

import com.pogreb.leasingshift.feature.carslist.domain.entity.CarsItem

interface CarsListRepository {
    suspend fun getAllCars(): List<CarsItem>
}