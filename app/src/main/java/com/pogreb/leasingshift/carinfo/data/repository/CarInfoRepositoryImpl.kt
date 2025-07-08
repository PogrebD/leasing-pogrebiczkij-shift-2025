package com.pogreb.leasingshift.carinfo.data.repository

import com.pogreb.leasingshift.carinfo.data.CarInfoApi
import com.pogreb.leasingshift.carinfo.data.converter.CarInfoConverter
import com.pogreb.leasingshift.carinfo.domain.entity.CarInfo
import com.pogreb.leasingshift.carinfo.domain.repository.CarInfoRepository
import javax.inject.Inject

class CarInfoRepositoryImpl @Inject constructor(
    private val api: CarInfoApi,
    private val converter: CarInfoConverter,
) : CarInfoRepository {
    override suspend fun getCar(id: Long): CarInfo =
        converter.convert(api.getCarById(id).data)
}