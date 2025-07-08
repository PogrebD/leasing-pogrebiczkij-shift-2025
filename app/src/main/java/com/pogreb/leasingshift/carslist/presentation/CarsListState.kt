package com.pogreb.leasingshift.carslist.presentation

import com.pogreb.leasingshift.carslist.domain.entity.CarsItem

sealed interface CarsListState {
    data class Idle(val cars: List<CarsItem>, val searchState: SearchState) : CarsListState
    data object Loading : CarsListState
    data class Error(val reason: String) : CarsListState
}