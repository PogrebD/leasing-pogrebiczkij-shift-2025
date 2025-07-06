package com.pogreb.leasingshift.carinfo.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Rents(
    val startDate: Long,
    val endDate: Long,
)
