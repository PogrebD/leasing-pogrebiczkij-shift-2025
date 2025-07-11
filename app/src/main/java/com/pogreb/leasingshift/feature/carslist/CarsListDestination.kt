package com.pogreb.leasingshift.feature.carslist

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pogreb.leasingshift.feature.carinfo.CarInfoRoute
import com.pogreb.leasingshift.feature.carslist.presentation.CarsListViewModel
import com.pogreb.leasingshift.feature.carslist.ui.CarsListScreen

fun NavGraphBuilder.carsList(navController: NavHostController) {
    composable<CarsListRoute> {
        val carsListViewModel: CarsListViewModel = hiltViewModel()

        CarsListScreen(
            carsListViewModel = carsListViewModel,
            onItemClick = { carId, date ->
                navController.navigate(
                    CarInfoRoute(carId, date)
                )
            },
        )
    }
}