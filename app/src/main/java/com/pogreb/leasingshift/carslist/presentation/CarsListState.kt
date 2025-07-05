package com.pogreb.leasingshift.carslist.presentation

import com.pogreb.leasingshift.carslist.domain.entity.CarsItem


sealed interface CarsListState {

    data object Loading : CarsListState

    data class Error(val message: String) : CarsListState

    data class Content(val cars: List<CarsItem>) : CarsListState
}
