package com.pogreb.leasingshift.carslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.leasingshift.carslist.di.BaseUrl
import com.pogreb.leasingshift.carslist.domain.Mapper
import com.pogreb.leasingshift.carslist.domain.entity.CarsItem
import com.pogreb.leasingshift.carslist.domain.entity.FilterParams
import com.pogreb.leasingshift.carslist.domain.usecase.GetCarsListUseCase
import com.pogreb.leasingshift.carslist.domain.usecase.GetFilteredCarsUseCase
import com.pogreb.leasingshift.carslist.domain.usecase.GetFoundCarsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsListViewModel @Inject constructor(
    private val getCarsListUseCase: GetCarsListUseCase,
    private val getFoundCarsUseCase: GetFoundCarsUseCase,
    private val getFilteredCarsUseCase: GetFilteredCarsUseCase,
    private val mapper: Mapper,
    @BaseUrl val baseUrl: String,
) : ViewModel() {
    private var currentFilterParams: FilterParams? = null
    private val _state = MutableStateFlow<CarsListState>(CarsListState.Loading)
    val state: StateFlow<CarsListState> = _state.asStateFlow()


    fun loadData() {
        _state.value = CarsListState.Loading

        viewModelScope.launch {
            try {
                val carsListItems = getCarsListUseCase.invoke()
                _state.value = CarsListState.Idle(
                    cars = carsListItems,
                    searchState = getSearchState(
                        query = "",
                        carsListItems
                    )
                )
            } catch (e: Exception) {
                _state.value = CarsListState.Error(e.message.orEmpty())
            }
        }
    }

    fun openFilter() {
        val currentState = _state.value as? CarsListState.Idle ?: return

        _state.value = CarsListState.Idle(
            cars = currentState.cars,
            searchState = SearchState.SelectFilter(
                query = currentState.searchState.query,
                brandName = currentFilterParams?.brandName ?: "",
                bodyTypeName = currentFilterParams?.bodyTypeName ?: "",
                steeringName = currentFilterParams?.steeringName ?: "",
                transmissionName = currentFilterParams?.transmissionName ?: "",
                colorName = currentFilterParams?.colorName ?: "",
                cars = currentState.cars,

                )
        )
    }

    fun filter(
        brandName: String?,
        bodyTypeName: String?,
        steeringName: String?,
        transmissionName: String?,
        colorName: String?,
    ) {
        val currentState = _state.value as? CarsListState.Idle ?: return
        currentFilterParams = FilterParams(
            brandName, bodyTypeName, steeringName, transmissionName, colorName
        )

        _state.value = CarsListState.Idle(
            cars = currentState.cars,
            searchState = getFilteredSearchState(
                currentState.searchState.query,
                brandName,
                bodyTypeName,
                steeringName,
                transmissionName,
                colorName,
                currentState.cars
            )
        )
    }

    private fun getFilteredSearchState(
        query: String,
        brandName: String?,
        bodyTypeName: String?,
        steeringName: String?,
        transmissionName: String?,
        colorName: String?,
        cars: List<CarsItem>
    ): SearchState {
        val filteredCars = getFilteredCarsUseCase(
            brandName,
            bodyTypeName,
            steeringName,
            transmissionName,
            colorName,
            cars,
            mapper
        )
        return SearchState.Found(
            query = query,
            cars = filteredCars,
        )
    }

    fun searchCars(query: String) {
        val currentState = _state.value as? CarsListState.Idle ?: return
        _state.value = CarsListState.Idle(
            cars = currentState.cars,
            getSearchState(
                query = query,
                cars = currentState.cars,
            )
        )
    }

    private fun getSearchState(query: String, cars: List<CarsItem>): SearchState {
        val filteredCars = getFoundCarsUseCase(query, cars)

        return if (filteredCars.isEmpty()) {
            SearchState.NotFound(query)
        } else {
            SearchState.Found(
                query = query,
                cars = filteredCars,
            )
        }
    }
}