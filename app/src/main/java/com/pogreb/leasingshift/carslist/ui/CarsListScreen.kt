package com.pogreb.leasingshift.carslist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.carslist.di.CarsListViewModelFactory
import com.pogreb.leasingshift.carslist.presentation.CarsListViewModel
import com.pogreb.leasingshift.ui.theme.CustomTextStyle
import com.pogreb.leasingshift.carslist.di.CarsListProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pogreb.leasingshift.carslist.presentation.Status
import com.pogreb.leasingshift.main.Title


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


