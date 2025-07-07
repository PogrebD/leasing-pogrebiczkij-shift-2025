package com.pogreb.leasingshift.carinfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.carinfo.presentation.CarInfoViewModel
import com.pogreb.leasingshift.carinfo.presentation.state.Status
import com.pogreb.leasingshift.main.ui.FullScreenProgressIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarInfoScreen(
    carInfoViewModel: CarInfoViewModel,
    carId: Long,
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
        when (val currentState = state.status) {

            Status.Loading -> FullScreenProgressIndicator()

            is Status.Idle -> CarInfoContent(
                car = currentState.car,
                onReserveClick = onReserveClick,
                onBackClick = onBackClick,
            )

            is Status.Error -> CarsListError(
                message = currentState.reason,
                onRetry = {
                    carInfoViewModel.loadData()
                }
            )
        }
    }

}