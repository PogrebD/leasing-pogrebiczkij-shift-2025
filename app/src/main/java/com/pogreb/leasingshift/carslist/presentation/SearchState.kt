package com.pogreb.leasingshift.carslist.presentation

import com.pogreb.leasingshift.carslist.domain.entity.CarsItem

sealed interface SearchState {

    val query: String

    data class SelectFilter(
        override val query: String,
        val brandName: String,
        val bodyTypeName: String,
        val steeringName: String,
        val transmissionName: String,
        val colorName: String,
        val cars: List<CarsItem>,
    ) : SearchState

    data class Found(
        override val query: String,
        val cars: List<CarsItem>,
    ) : SearchState

    data class NotFound(override val query: String) : SearchState
}