package com.pogreb.leasingshift.carslist

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pogreb.leasingshift.carinfo.CarInfoRoute
import com.pogreb.leasingshift.carslist.presentation.CarsListViewModel
import com.pogreb.leasingshift.carslist.ui.CarsListScreen

fun NavGraphBuilder.carsList(navController: NavHostController) {
    composable<CarsListRoute> {
        val carsListViewModel: CarsListViewModel = hiltViewModel()

        CarsListScreen(
            carsListViewModel = carsListViewModel,
            onItemClick = { carId ->
                navController.navigate(CarInfoRoute(carId))
            }
        )
    }
}