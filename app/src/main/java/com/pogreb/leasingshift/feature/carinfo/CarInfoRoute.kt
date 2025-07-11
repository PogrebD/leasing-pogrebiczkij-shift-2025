package com.pogreb.leasingshift.feature.carinfo

import com.pogreb.leasingshift.shared.domain.entity.DateBooking
import kotlinx.serialization.Serializable

@Serializable
data class CarInfoRoute(
    val carId: Long,
    val date: DateBooking,
)