package com.pogreb.leasingshift.feature.carinfo

import kotlinx.serialization.Serializable

@Serializable
data class CarInfoRoute(
    val carId: Long
)