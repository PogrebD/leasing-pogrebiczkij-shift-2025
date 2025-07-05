package com.pogreb.leasingshift.carslist.presentation

import com.pogreb.leasingshift.carslist.domain.entity.CarsItem

sealed interface SearchState {

    val query: String

    data class Found(
        override val query: String,
        val cars: List<CarsItem>,
    ) : SearchState

    data class NotFound(override val query: String) : SearchState
}