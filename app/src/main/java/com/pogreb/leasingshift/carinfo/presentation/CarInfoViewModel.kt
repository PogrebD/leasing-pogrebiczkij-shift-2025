package com.pogreb.leasingshift.carinfo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.leasingshift.carinfo.domain.usecase.GetCarInfoUseCase
import com.pogreb.leasingshift.carinfo.presentation.state.CarInfoState
import com.pogreb.leasingshift.carinfo.presentation.state.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CarInfoViewModel(
    private val id: Long,
    private val getCarInfoUseCase: GetCarInfoUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<CarInfoState>(CarInfoState(Status.Loading))
    val state: StateFlow<CarInfoState> = _state.asStateFlow()

    fun loadData() {

        _state.update { it.copy(status = Status.Loading) }

        viewModelScope.launch {
            try {
                val carInfo = getCarInfoUseCase.invoke(id)
                _state.update {
                    it.copy(
                        status = Status.Idle(
                            car = carInfo
                        )
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(status = Status.Error(e.message.orEmpty())) }
            }
        }
    }
}