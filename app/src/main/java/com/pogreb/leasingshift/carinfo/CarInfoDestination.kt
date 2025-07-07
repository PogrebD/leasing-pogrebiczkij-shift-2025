package com.pogreb.leasingshift.carinfo

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.pogreb.leasingshift.carinfo.di.CarInfoProvider
import com.pogreb.leasingshift.carinfo.di.CarInfoViewModelFactory
import com.pogreb.leasingshift.carinfo.presentation.CarInfoViewModel
import com.pogreb.leasingshift.carinfo.ui.CarInfoScreen
import com.pogreb.leasingshift.carslist.CarsListRoute

fun NavGraphBuilder.carInfo(navController: NavHostController) {
    composable<CarInfoRoute> {
        val destination = it.toRoute<CarInfoRoute>()
        val carInfoViewModel: CarInfoViewModel = viewModel(
            factory = CarInfoViewModelFactory(
                CarInfoProvider.getCarInfoUseCase,
                destination.carId,
            )
        )

        CarInfoScreen(
            carInfoViewModel = carInfoViewModel,
            onReserveClick = {},
            onBackClick = { navController.navigate(route = CarsListRoute) },
        )
    }
}