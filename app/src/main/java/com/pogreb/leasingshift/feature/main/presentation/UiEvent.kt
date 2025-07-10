package com.pogreb.leasingshift.feature.main.presentation

sealed class UiEvent {
    data class Navigate(val route: Any) : UiEvent()
    object PopBackStack : UiEvent()
}