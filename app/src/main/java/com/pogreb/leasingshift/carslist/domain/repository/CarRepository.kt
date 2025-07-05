package com.pogreb.leasingshift.carslist.domain.repository

import com.pogreb.leasingshift.carslist.domain.entity.CarsItem

interface CarRepository {
    suspend fun getAllCars(): List<CarsItem>
}