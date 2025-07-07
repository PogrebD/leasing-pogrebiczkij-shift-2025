package com.pogreb.leasingshift.carslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.leasingshift.carslist.domain.entity.CarsItem
import com.pogreb.leasingshift.carslist.domain.usecase.GetCarsListUseCase
import com.pogreb.leasingshift.carslist.domain.usecase.GetFoundCarsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CarsListViewModel(
    private val getCarsListUseCase: GetCarsListUseCase,
    private val getFoundCarsUseCase: GetFoundCarsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<CarsListState>(CarsListState(Status.Loading))
    val state: StateFlow<CarsListState> = _state.asStateFlow()

    fun loadData() {

        _state.update { it.copy(status = Status.Loading) }

        viewModelScope.launch {
            try {
                val carsListItems = getCarsListUseCase.invoke()
                _state.update {
                    it.copy(
                        status = Status.Idle(
                            cars = carsListItems,
                            searchState = getSearchState(
                                query = "",
                                carsListItems
                            )
                        )
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(status = Status.Error(e.message.orEmpty())) }
            }
        }
    }

    fun searchCars(query: String) {
        val currentState = _state.value.status as? Status.Idle ?: return
        _state.update {
            it.copy(
                status = Status.Idle(
                    cars = currentState.cars,
                    getSearchState(
                        query,
                        cars = currentState.cars,
                    )
                )
            )
        }
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