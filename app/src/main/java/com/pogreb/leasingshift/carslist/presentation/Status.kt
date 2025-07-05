package com.pogreb.leasingshift.carslist.presentation

import com.pogreb.leasingshift.carslist.domain.entity.CarsItem

sealed interface Status {
    data class Idle(val cars: List<CarsItem>, val searchState: SearchState) : Status
    data object Loading : Status
    data class Error(val reason: String) : Status
}