package com.pogreb.leasingshift.feature.carinfo.domain.usecase

import com.pogreb.leasingshift.feature.carinfo.domain.entity.CarInfo
import com.pogreb.leasingshift.feature.carinfo.domain.repository.CarInfoRepository
import javax.inject.Inject

class GetCarInfoUseCase @Inject constructor(private val carInfoRepository: CarInfoRepository) {

    suspend fun invoke(id: Long): CarInfo =
        carInfoRepository.getCar(id)
}