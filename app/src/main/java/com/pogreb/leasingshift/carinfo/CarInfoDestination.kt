package com.pogreb.leasingshift.carinfo

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pogreb.leasingshift.carinfo.presentation.CarInfoViewModel
import com.pogreb.leasingshift.carinfo.ui.CarInfoScreen

fun NavGraphBuilder.carInfo(navController: NavHostController) {
    composable<CarInfoRoute> {
        val carInfoViewModel: CarInfoViewModel = hiltViewModel()

        CarInfoScreen(
            carInfoViewModel = carInfoViewModel,
            onReserveClick = {},
            onBackClick = { navController.popBackStack() },
        )
    }
}