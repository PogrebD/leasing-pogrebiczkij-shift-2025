package com.pogreb.leasingshift.feature.carslist.domain.usecase

import com.pogreb.leasingshift.feature.carslist.domain.entity.CarsItem
import javax.inject.Inject

class GetFoundCarsUseCase @Inject constructor() {

    operator fun invoke(query: String, cars: List<CarsItem>): List<CarsItem> =
        cars.filter {
            it.name.contains(query, ignoreCase = true)
        }
}