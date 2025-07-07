package com.pogreb.leasingshift.carinfo.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val reason: String? = null,
    val data: CarInfoModel,
)
