package com.pogreb.leasingshift.feature.carinfo.presentation

import com.pogreb.leasingshift.feature.carinfo.domain.entity.CarInfo

sealed interface CarInfoState {
    data class Idle(val car: CarInfo) : CarInfoState
    data object Loading : CarInfoState
    data class Error(val reason: String) : CarInfoState
}