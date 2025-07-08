package com.pogreb.leasingshift.carslist.domain.usecase

import com.pogreb.leasingshift.carslist.domain.entity.CarsItem
import com.pogreb.leasingshift.carslist.domain.repository.CarsListRepository
import javax.inject.Inject

class GetCarsListUseCase @Inject constructor(private val carsRepository: CarsListRepository) {

    suspend fun invoke(): List<CarsItem> =
        carsRepository.getAllCars()
}