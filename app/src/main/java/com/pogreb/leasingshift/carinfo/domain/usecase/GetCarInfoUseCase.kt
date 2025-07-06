package com.pogreb.leasingshift.carinfo.domain.usecase

import com.pogreb.leasingshift.carinfo.domain.entity.CarInfo
import com.pogreb.leasingshift.carinfo.domain.repository.CarInfoRepository

class GetCarInfoUseCase(private val carInfoRepository: CarInfoRepository) {

    suspend fun invoke(id: Long): CarInfo =
        carInfoRepository.getCar(id)
}