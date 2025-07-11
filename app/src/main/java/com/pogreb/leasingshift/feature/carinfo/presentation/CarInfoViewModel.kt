package com.pogreb.leasingshift.feature.carinfo.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.leasingshift.feature.carinfo.domain.usecase.GetCarInfoUseCase
import com.pogreb.leasingshift.feature.carinfo.domain.entity.BookingModel
import com.pogreb.leasingshift.feature.carinfo.domain.entity.UserInformationModel
import com.pogreb.leasingshift.shared.di.BaseUrl
import com.pogreb.leasingshift.shared.domain.entity.DateBooking
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
    private val date: DateBooking? = savedStateHandle.get<DateBooking>("date")
    private val _state = MutableStateFlow<CarInfoState>(CarInfoState.Loading)
    val state: StateFlow<CarInfoState> = _state.asStateFlow()

    private val _bookingData = MutableStateFlow(BookingModel())
    val bookingData: StateFlow<BookingModel> = _bookingData.asStateFlow()

    private val _userInfoData = MutableStateFlow(UserInformationModel())
    val userInfoData: StateFlow<UserInformationModel> = _userInfoData.asStateFlow()

    fun loadData() {
        _state.value = CarInfoState.Loading

        viewModelScope.launch {
            try {
                val carId = requireNotNull(id) { "Argument carId cannot be null" }
                val date = requireNotNull(date) { "Argument date cannot be null" }
                val carInfo = getCarInfoUseCase.invoke(carId)

                _bookingData.value =
                    _bookingData.value.copy(
                        dateBooking = date,
                        price = carInfo.price
                    )

                _state.value = CarInfoState.Idle(
                    car = carInfo,
                    bookingState = CarBookingState.Content
                )
            } catch (e: Exception) {
                _state.value = CarInfoState.Error(e.message.orEmpty())
            }
        }
    }

    fun updateBookingData(data: BookingModel) {
        _bookingData.value = data.copy(
            carName = (_state.value as CarInfoState.Idle).car.name
        )
    }

    fun updateUserInfoData(data: UserInformationModel) {
        _userInfoData.value = data
    }

    fun switchBookingState() {
        val currentState = _state.value as? CarInfoState.Idle ?: return
        when (currentState.bookingState) {
            CarBookingState.Content -> _state.value =
                CarInfoState.Idle(currentState.car, CarBookingState.Booking)

            CarBookingState.Booking -> _state.value =
                CarInfoState.Idle(currentState.car, CarBookingState.UserInformation)

            CarBookingState.UserInformation -> _state.value =
                CarInfoState.Idle(currentState.car, CarBookingState.DataVerification)

            CarBookingState.DataVerification -> _state.value =
                CarInfoState.Idle(currentState.car, CarBookingState.SuccessfulResult)

            CarBookingState.SuccessfulResult -> _state.value =
                CarInfoState.Idle(currentState.car, CarBookingState.Content)

        }
    }

    fun switchBackBookingState() {
        val currentState = _state.value as? CarInfoState.Idle ?: return
        when (currentState.bookingState) {
            CarBookingState.Content -> {}

            CarBookingState.Booking -> _state.value =
                CarInfoState.Idle(currentState.car, CarBookingState.Content)

            CarBookingState.UserInformation -> _state.value =
                CarInfoState.Idle(currentState.car, CarBookingState.Booking)

            CarBookingState.DataVerification -> _state.value =
                CarInfoState.Idle(currentState.car, CarBookingState.Content)

            CarBookingState.SuccessfulResult -> {}

        }
    }
}