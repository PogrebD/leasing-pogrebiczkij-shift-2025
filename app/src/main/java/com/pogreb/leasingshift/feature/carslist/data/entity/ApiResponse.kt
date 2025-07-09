package com.pogreb.leasingshift.feature.carslist.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val reason: String? = null,
    val data: List<CarsItemModel>,
)