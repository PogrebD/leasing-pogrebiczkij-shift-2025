package com.pogreb.leasingshift.feature.carinfo.domain.entity

import com.pogreb.leasingshift.shared.domain.entity.DateBooking
import java.time.LocalDate
import java.time.ZoneId

data class BookingModel(
    val carName: String = "",
    val dateBooking: DateBooking = DateBooking(
        startDate = LocalDate.now()
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli(),
        endDate = LocalDate.now().plusDays(1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli(),
        numDays = 1L
    ),
    val placeReceiving: String = "",
    val placeReturn: String = "",
    val price: Long = 0L,
)
