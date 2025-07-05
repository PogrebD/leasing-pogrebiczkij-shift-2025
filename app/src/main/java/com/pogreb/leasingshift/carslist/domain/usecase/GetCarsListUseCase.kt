package com.pogreb.leasingshift.carslist.domain.usecase

import com.pogreb.leasingshift.carslist.domain.entity.CarsItem
import com.pogreb.leasingshift.carslist.domain.repository.CarRepository

class GetCarsListUseCase(private val carsRepository: CarRepository) {

    suspend fun invoke(): List<CarsItem> =
        carsRepository.getAllCars()
}