package com.pogreb.leasingshift.feature.carinfo.domain.repository

import com.pogreb.leasingshift.feature.carinfo.domain.entity.CarInfo

interface CarInfoRepository {
    suspend fun getCar(id: Long): CarInfo
}