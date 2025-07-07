package com.pogreb.leasingshift.carinfo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.leasingshift.carinfo.domain.usecase.GetCarInfoUseCase
import com.pogreb.leasingshift.carinfo.presentation.state.CarInfoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CarInfoViewModel(
    private val id: Long,
    private val getCarInfoUseCase: GetCarInfoUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<CarInfoState>(CarInfoState.Loading)
    val state: StateFlow<CarInfoState> = _state.asStateFlow()

    fun loadData() {

        _state.value = CarInfoState.Loading

        viewModelScope.launch {
            try {
                val carInfo = getCarInfoUseCase.invoke(id)
                _state.value = CarInfoState.Idle(
                    car = carInfo
                )
            } catch (e: Exception) {
                _state.value = CarInfoState.Error(e.message.orEmpty())
            }
        }
    }
}