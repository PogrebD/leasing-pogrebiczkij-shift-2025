package com.pogreb.leasingshift.carinfo.presentation.state

import com.pogreb.leasingshift.carinfo.domain.entity.CarInfo

sealed interface CarInfoState {
    data class Idle(val car: CarInfo) : CarInfoState
    data object Loading : CarInfoState
    data class Error(val reason: String) : CarInfoState
}