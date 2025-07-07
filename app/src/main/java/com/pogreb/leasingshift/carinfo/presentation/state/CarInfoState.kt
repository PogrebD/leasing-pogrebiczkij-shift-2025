package com.pogreb.leasingshift.carinfo.presentation.state

data class CarInfoState(
    val status: Status = Status.Loading,
)