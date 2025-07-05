package com.pogreb.leasingshift.carslist.data


import com.pogreb.leasingshift.carslist.data.entity.ApiResponse
import retrofit2.http.GET

interface CarsApi {
    @GET("/api/cars/info")
    suspend fun getAllCars(): ApiResponse

}