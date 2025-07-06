package com.pogreb.leasingshift.carslist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.carslist.presentation.CarsListViewModel
import com.pogreb.leasingshift.carslist.presentation.Status
import com.pogreb.leasingshift.main.ui.FullScreenProgressIndicator
import com.pogreb.leasingshift.main.ui.Title


@Composable
fun CarsListScreen(
    carsListViewModel: CarsListViewModel,
    onItemClick: (loanId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by carsListViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        carsListViewModel.loadData()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Title(R.string.cars)

        when (val currentState = state.status) {

            Status.Loading -> FullScreenProgressIndicator()

            is Status.Idle -> CarsListContent(
                state = currentState,
                onItemClick = onItemClick,
                onSearchValueChange = { carsListViewModel.searchCars(it) },
            )

            is Status.Error -> CarsListError(
                message = currentState.reason,
                onRetry = {
                    carsListViewModel.loadData()
                }
            )
        }
    }
}


