package com.pogreb.leasingshift.carslist.domain.repository

import com.pogreb.leasingshift.carslist.domain.entity.CarsItem

interface CarsListRepository {
    suspend fun getAllCars(): List<CarsItem>
}