package com.pogreb.leasingshift.main.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.pogreb.leasingshift.carslist.CarsListRoute
import com.pogreb.leasingshift.main.domain.entity.NavigationOption
import com.pogreb.leasingshift.main.ui.navigateToRoot
import com.pogreb.leasingshift.orders.OrdersRoute
import com.pogreb.leasingshift.profile.ProfileRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val SELECTED_TAB_KEY = "selected_tab"
    }

    private val _selectedTab = MutableStateFlow(
        savedStateHandle.get<String>(SELECTED_TAB_KEY)?.let {
            NavigationOption.valueOf(it)
        } ?: NavigationOption.CARSLIST
    )
    val selectedTab: StateFlow<NavigationOption> = _selectedTab

    val tabs = TabDataProvider.getTabs()

    init {
        viewModelScope.launch {
            _selectedTab.collect { tab ->
                savedStateHandle[SELECTED_TAB_KEY] = tab.name
            }
        }
    }

    fun handleBottomNavClick(navOption: NavigationOption, navController: NavController) {
        if (selectedTab.value == navOption) return

        _selectedTab.value = navOption
        navigateToTab(navOption, navController)
    }

    private fun navigateToTab(navOption: NavigationOption, navController: NavController) {
        val route = when (navOption) {
            NavigationOption.CARSLIST -> CarsListRoute
            NavigationOption.ORDERS -> OrdersRoute
            NavigationOption.PROFILE -> ProfileRoute
        }
        navController.navigateToRoot(route)
    }
}