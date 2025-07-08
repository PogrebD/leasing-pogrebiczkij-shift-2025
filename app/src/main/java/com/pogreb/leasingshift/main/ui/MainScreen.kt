package com.pogreb.leasingshift.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pogreb.leasingshift.carinfo.carInfo
import com.pogreb.leasingshift.carslist.CarsListRoute
import com.pogreb.leasingshift.carslist.carsList
import com.pogreb.leasingshift.main.presentation.MainViewModel
import com.pogreb.leasingshift.orders.orders
import com.pogreb.leasingshift.profile.profile

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()
    val selectedTab by mainViewModel.selectedTab.collectAsState()

    Scaffold() { paddingValues: PaddingValues ->
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = navController,
                startDestination = CarsListRoute,
            ) {
                carsList(navController)
                carInfo(navController)
                profile()
                orders()
            }
            BottomNavigation(
                mainViewModel = mainViewModel,
                selectedNavigationOption = selectedTab,
                onItemClicked = { navOption ->
                    mainViewModel.handleBottomNavClick(navOption, navController)
                }
            )
        }
    }
}