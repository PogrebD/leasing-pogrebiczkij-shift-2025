package com.pogreb.leasingshift.carinfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.carinfo.presentation.CarInfoViewModel
import com.pogreb.leasingshift.carinfo.presentation.state.CarInfoState
import com.pogreb.leasingshift.main.ui.FullScreenProgressIndicator

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        when (val currentState = state) {

            CarInfoState.Loading -> FullScreenProgressIndicator()

            is CarInfoState.Idle -> CarInfoContent(
                car = currentState.car,
                onReserveClick = onReserveClick,
                onBackClick = onBackClick,
            )

            is CarInfoState.Error -> CarsListError(
                message = currentState.reason,
                onRetry = {
                    carInfoViewModel.loadData()
                }
            )
        }
    }

}