package com.pogreb.leasingshift.feature.carslist.domain.usecase

import com.pogreb.leasingshift.feature.carslist.domain.Mapper
import com.pogreb.leasingshift.feature.carslist.domain.entity.CarsItem
import javax.inject.Inject

class GetFilteredCarsUseCase @Inject constructor() {

    operator fun invoke(
        brandName: String?,
        bodyTypeName: String?,
        steeringName: String?,
        transmissionName: String?,
        colorName: String?,
        cars: List<CarsItem>,
        mapper: Mapper,
    ): List<CarsItem> {
        return cars.filter { car ->
            (brandName.isNullOrEmpty() || car.brand == brandName.let { mapper.mapBrand(it) }) &&
                    (bodyTypeName.isNullOrEmpty() || car.bodyType == bodyTypeName.let { mapper.mapBodyType(it) }) &&
                    (steeringName.isNullOrEmpty() || car.steering == steeringName.let { mapper.mapSteering(it) }) &&
                    (transmissionName.isNullOrEmpty() || car.transmission == transmissionName.let { mapper.mapTransmission(it) }) &&
                    (colorName.isNullOrEmpty() || car.color == colorName.let { mapper.mapColor(it) })
        }
    }
}