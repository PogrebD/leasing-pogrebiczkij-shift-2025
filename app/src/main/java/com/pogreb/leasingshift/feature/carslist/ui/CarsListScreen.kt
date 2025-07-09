package com.pogreb.leasingshift.feature.carslist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.feature.carslist.presentation.CarsListState
import com.pogreb.leasingshift.feature.carslist.presentation.CarsListViewModel
import com.pogreb.leasingshift.feature.carslist.presentation.SearchState
import com.pogreb.leasingshift.main.ui.FullScreenProgressIndicator
import com.pogreb.leasingshift.main.ui.Title
import com.pogreb.leasingshift.shared.ui.ErrorMassage


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
        Title(R.string.cars_title)

        when (val currentState = state) {

            CarsListState.Loading -> FullScreenProgressIndicator()

            is CarsListState.Idle ->
                when (val currentSearchState = currentState.searchState) {
                    is SearchState.SelectFilter -> FilterScreen(
                        currentSearchState,
                        onBackClick = { carsListViewModel.searchCars(currentSearchState.query) },
                        onFilterSearchClick = {
                            carsListViewModel.filter(
                                it.brandName,
                                it.bodyTypeName,
                                it.steeringName,
                                it.transmissionName,
                                it.colorName
                            )
                        },
                    )

                    is SearchState.Found -> CarsListContent(
                        state = currentState,
                        onItemClick = onItemClick,
                        onSearchValueChange = { carsListViewModel.searchCars(it) },
                        onFilterClick = carsListViewModel::openFilter,
                        baseUrl = carsListViewModel.baseUrl
                    )

                    is SearchState.NotFound -> CarsListContent(
                        state = currentState,
                        onItemClick = onItemClick,
                        onSearchValueChange = { carsListViewModel.searchCars(it) },
                        onFilterClick = carsListViewModel::openFilter,
                        baseUrl = carsListViewModel.baseUrl
                    )
                }


            is CarsListState.Error -> ErrorMassage(
                message = currentState.reason,
                onRetry = carsListViewModel::loadData
            )
        }
    }
}