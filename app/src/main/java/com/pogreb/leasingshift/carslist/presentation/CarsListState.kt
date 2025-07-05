package com.pogreb.leasingshift.carslist.presentation

import com.pogreb.leasingshift.carslist.domain.entity.CarsItem
import com.pogreb.leasingshift.main.entity.Status


data class CarsListState(
    val cars: List<CarsItem>? = null,
    val status: Status = Status.Idle,
)
