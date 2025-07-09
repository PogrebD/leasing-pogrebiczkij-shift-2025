package com.pogreb.leasingshift.feature.carslist.data

import com.pogreb.leasingshift.feature.carslist.data.entity.ApiResponse
import retrofit2.http.GET

interface CarsListApi {
    @GET("/api/cars/info")
    suspend fun getAllCars(): ApiResponse

}