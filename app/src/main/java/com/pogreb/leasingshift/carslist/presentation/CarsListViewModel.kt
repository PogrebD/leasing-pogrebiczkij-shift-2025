package com.pogreb.leasingshift.carslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.leasingshift.carslist.domain.usecase.GetCarsListUseCase
import com.pogreb.leasingshift.main.entity.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CarsListViewModel(
    private val getCarsListUseCase: GetCarsListUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<CarsListState>(CarsListState())
    val state: StateFlow<CarsListState> = _state.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {

        _state.update { it.copy(status = Status.Loading)}

        viewModelScope.launch {
            try {
                val carsListItems = getCarsListUseCase.invoke()
                _state.update { it.copy(cars = carsListItems, status = Status.Idle) }
            } catch (e: Exception) {
                _state.update { it.copy(status = Status.Error(e.message.orEmpty())) }
            }
        }
    }

}