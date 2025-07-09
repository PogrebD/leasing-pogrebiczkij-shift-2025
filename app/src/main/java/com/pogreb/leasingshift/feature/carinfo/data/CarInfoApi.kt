package com.pogreb.leasingshift.feature.carinfo.data

import com.pogreb.leasingshift.feature.carinfo.data.entity.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CarInfoApi {
    @GET("/api/cars/info/{carId}")
    suspend fun getCarById(@Path("carId") carId: Long): ApiResponse

}