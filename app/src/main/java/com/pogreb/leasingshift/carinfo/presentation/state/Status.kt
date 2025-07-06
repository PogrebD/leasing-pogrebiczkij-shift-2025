package com.pogreb.leasingshift.carinfo.presentation.state

import com.pogreb.leasingshift.carinfo.domain.entity.CarInfo

sealed interface Status {
    data class Idle(val cars: CarInfo) : Status
    data object Loading : Status
    data class Error(val reason: String) : Status
}