package com.pogreb.leasingshift.feature.carinfo.presentation

sealed interface CarBookingState {
    data object Content : CarBookingState
    data object Booking : CarBookingState
    data object UserInformation : CarBookingState
    data object DataVerification : CarBookingState
    data object SuccessfulResult : CarBookingState
}