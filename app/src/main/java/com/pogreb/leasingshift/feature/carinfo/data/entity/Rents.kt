package com.pogreb.leasingshift.feature.carinfo.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Rents(
    val startDate: Long,
    val endDate: Long,
)
