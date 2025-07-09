package com.pogreb.leasingshift.feature.carinfo.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.pogreb.leasingshift.feature.carinfo.presentation.CarInfoState
import com.pogreb.leasingshift.feature.carinfo.presentation.CarInfoViewModel
import com.pogreb.leasingshift.main.ui.FullScreenProgressIndicator
import com.pogreb.leasingshift.shared.ui.ErrorMassage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarInfoScreen(
    carInfoViewModel: CarInfoViewModel,
    onReserveClick: () -> Unit,
    onBackClick: () -> Unit,

) {
    val state by carInfoViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        carInfoViewModel.loadData()
    }

    when (val currentState = state) {

        CarInfoState.Loading -> FullScreenProgressIndicator()

        is CarInfoState.Idle -> CarInfoContent(
            car = currentState.car,
            onReserveClick = onReserveClick,
            onBackClick = onBackClick,
            baseUrl = carInfoViewModel.baseUrl
        )

        is CarInfoState.Error -> ErrorMassage(
            message = currentState.reason,
            onRetry = {
                carInfoViewModel.loadData()
            }
        )
    }
}