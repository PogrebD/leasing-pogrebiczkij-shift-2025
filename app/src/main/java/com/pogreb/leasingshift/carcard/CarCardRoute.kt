package com.pogreb.leasingshift.carcard

import kotlinx.serialization.Serializable

@Serializable
data class CarCardRoute(
    val carId: Long
)