package com.pogreb.leasingshift.feature.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pogreb.leasingshift.feature.carinfo.carInfo
import com.pogreb.leasingshift.feature.carslist.CarsListRoute
import com.pogreb.leasingshift.feature.carslist.carsList
import com.pogreb.leasingshift.feature.main.presentation.MainViewModel
import com.pogreb.leasingshift.feature.main.presentation.UiEvent
import com.pogreb.leasingshift.feature.orders.orders
import com.pogreb.leasingshift.feature.profile.profile
import com.pogreb.leasingshift.main.ui.navigateToRoot

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()

    val lifeCycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(mainViewModel, lifeCycleOwner) {
        lifeCycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            mainViewModel.eventsFlow.collect { event ->
                when (event) {
                    is UiEvent.Navigate -> navController.navigateToRoot(event.route)
                    UiEvent.PopBackStack -> navController.popBackStack()
                }
            }
        }
    }

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
                onItemClicked = { navOption ->
                    mainViewModel.navigateTo(navOption)
                },
                navController = navController
            )
        }
    }
}