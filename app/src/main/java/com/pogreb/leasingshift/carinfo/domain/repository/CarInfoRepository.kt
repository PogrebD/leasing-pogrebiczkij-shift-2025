package com.pogreb.leasingshift.carinfo.domain.repository

import com.pogreb.leasingshift.carinfo.domain.entity.CarInfo

interface CarInfoRepository {
    suspend fun getCar(id: Long): CarInfo
}