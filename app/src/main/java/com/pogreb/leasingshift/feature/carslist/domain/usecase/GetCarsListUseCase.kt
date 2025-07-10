package com.pogreb.leasingshift.feature.carslist.domain.usecase

import com.pogreb.leasingshift.feature.carslist.domain.entity.CarsItem
import com.pogreb.leasingshift.feature.carslist.domain.repository.CarsListRepository
import javax.inject.Inject

class GetCarsListUseCase @Inject constructor(private val carsRepository: CarsListRepository) {

    suspend fun invoke(): List<CarsItem> =
        carsRepository.getAllCars()
}