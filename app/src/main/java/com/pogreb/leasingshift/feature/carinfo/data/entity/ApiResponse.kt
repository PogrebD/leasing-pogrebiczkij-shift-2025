package com.pogreb.leasingshift.feature.carinfo.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val reason: String? = null,
    val data: CarInfoModel,
)
