package com.pogreb.leasingshift.feature.carinfo.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.leasingshift.feature.carinfo.domain.usecase.GetCarInfoUseCase
import com.pogreb.leasingshift.shared.di.BaseUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarInfoViewModel @Inject constructor(
    private val getCarInfoUseCase: GetCarInfoUseCase,
    savedStateHandle: SavedStateHandle,
    @BaseUrl val baseUrl: String,
) : ViewModel() {
    private val id: Long? = savedStateHandle.get<Long>("carId")
    private val _state = MutableStateFlow<CarInfoState>(CarInfoState.Loading)
    val state: StateFlow<CarInfoState> = _state.asStateFlow()

    fun loadData() {
        _state.value = CarInfoState.Loading

        viewModelScope.launch {
            try {
                val carId = requireNotNull(id) { "Argument carId cannot be null" }
                val carInfo = getCarInfoUseCase.invoke(carId)
                _state.value = CarInfoState.Idle(
                    car = carInfo
                )
            } catch (e: Exception) {
                _state.value = CarInfoState.Error(e.message.orEmpty())
            }
        }
    }
}