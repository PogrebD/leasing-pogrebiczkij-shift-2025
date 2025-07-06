package com.pogreb.leasingshift.carinfo.data

import com.pogreb.leasingshift.carinfo.data.entity.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CarInfoApi {
    @GET("/api/car/{carId}")
    suspend fun getCarById(@Path("carId") carId: Long): ApiResponse

}