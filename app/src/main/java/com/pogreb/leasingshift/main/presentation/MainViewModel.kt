package com.pogreb.leasingshift.main.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.carslist.CarsListRoute
import com.pogreb.leasingshift.main.entity.enums.NavigationOption
import com.pogreb.leasingshift.main.ui.navigateToRoot
import com.pogreb.leasingshift.orders.OrdersRoute
import com.pogreb.leasingshift.profile.ProfileRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
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

    init {
        viewModelScope.launch {
            _selectedTab.collect { tab ->
                savedStateHandle[SELECTED_TAB_KEY] = tab.name
            }
        }
    }

    fun selectTab(tab: NavigationOption) {
        _selectedTab.value = tab
    }

    fun handleBottomNavClick(navOption: NavigationOption, navController: NavController) {
        if (selectedTab.value == navOption) return

        selectTab(navOption)
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

    fun getIcon(option: NavigationOption): ImageVector =
        when (option) {
            NavigationOption.CARSLIST -> Icons.Default.ShoppingCart
            NavigationOption.ORDERS -> Icons.Default.Favorite
            NavigationOption.PROFILE -> Icons.Default.AccountCircle
        }

    fun getLabel(option: NavigationOption): Int =
        when (option) {
            NavigationOption.CARSLIST -> R.string.cars
            NavigationOption.ORDERS -> R.string.orders
            NavigationOption.PROFILE -> R.string.profile
        }
}