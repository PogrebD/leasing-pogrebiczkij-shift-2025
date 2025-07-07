package com.pogreb.leasingshift.carinfo

import kotlinx.serialization.Serializable

@Serializable
data class CarInfoRoute(
    val carId: Long
)