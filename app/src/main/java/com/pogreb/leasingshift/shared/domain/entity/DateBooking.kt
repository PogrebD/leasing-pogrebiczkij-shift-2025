package com.pogreb.leasingshift.shared.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class DateBooking(
    val startDate:Long,
    val endDate:Long,
    val numDays:Long,
)
