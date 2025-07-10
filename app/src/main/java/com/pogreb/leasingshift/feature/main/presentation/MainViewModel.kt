package com.pogreb.leasingshift.feature.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.leasingshift.feature.carslist.CarsListRoute
import com.pogreb.leasingshift.feature.main.domain.entity.NavigationOption
import com.pogreb.leasingshift.feature.orders.OrdersRoute
import com.pogreb.leasingshift.feature.profile.ProfileRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _eventChannel = Channel<UiEvent>()
    val eventsFlow = _eventChannel.receiveAsFlow()

    fun triggerEvent(event: UiEvent) {
        viewModelScope.launch {
            _eventChannel.send(event)
        }
    }

    fun navigateTo(navOption: NavigationOption) {
        triggerEvent(UiEvent.Navigate(when (navOption) {
            NavigationOption.CARSLIST -> CarsListRoute
            NavigationOption.ORDERS -> OrdersRoute
            NavigationOption.PROFILE -> ProfileRoute
        }))
    }
}