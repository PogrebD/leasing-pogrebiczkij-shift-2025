package com.pogreb.leasingshift.carslist

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pogreb.leasingshift.carinfo.CarInfoRoute
import com.pogreb.leasingshift.carslist.di.CarsListProvider
import com.pogreb.leasingshift.carslist.di.CarsListViewModelFactory
import com.pogreb.leasingshift.carslist.presentation.CarsListViewModel
import com.pogreb.leasingshift.carslist.ui.CarsListScreen

fun NavGraphBuilder.carsList(navController: NavHostController) {
    composable<CarsListRoute> {
        val carsListViewModel: CarsListViewModel = viewModel(
            factory = CarsListViewModelFactory(
                CarsListProvider.getCarsListUseCase,
                CarsListProvider.getFoundCarsUseCase,
            )
        )

        CarsListScreen(
            carsListViewModel = carsListViewModel,
            onItemClick = { carId ->
                navController.navigate(CarInfoRoute(carId))
            }
        )
    }
}