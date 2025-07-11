package com.pogreb.leasingshift.feature.carslist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.feature.carslist.presentation.CarsListState
import com.pogreb.leasingshift.feature.carslist.presentation.CarsListViewModel
import com.pogreb.leasingshift.feature.carslist.presentation.SearchState
import com.pogreb.leasingshift.main.ui.FullScreenProgressIndicator
import com.pogreb.leasingshift.shared.domain.entity.DateBooking
import com.pogreb.leasingshift.shared.ui.components.Title
import com.pogreb.leasingshift.shared.ui.ErrorMassage
import com.pogreb.leasingshift.ui.theme.CustomTextStyle


@Composable
fun CarsListScreen(
    carsListViewModel: CarsListViewModel,
    onItemClick: (Long, DateBooking) -> Unit,
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
        when (val currentState = state) {

            CarsListState.Loading -> FullScreenProgressIndicator()

            is CarsListState.Idle ->
                when (val currentSearchState = currentState.searchState) {
                    is SearchState.SelectFilter -> {
                        Row {
                            Text(
                                text = stringResource(R.string.cars_title),
                                style = CustomTextStyle.appTitle,
                                modifier = Modifier
                                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                                    .weight(1f)
                                    .height(56.dp),
                            )
                            ExitButton { carsListViewModel.searchCars(currentSearchState.query) }
                        }
                        FilterScreen(
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
                    }

                    is SearchState.Found -> {
                        Title(R.string.cars_title)
                        CarsListContent(
                            state = currentState,
                            onItemClick = onItemClick,
                            onSearchValueChange = { carsListViewModel.searchCars(it) },
                            onFilterClick = carsListViewModel::openFilter,
                            baseUrl = carsListViewModel.baseUrl,
                        )
                    }

                    is SearchState.NotFound -> CarsListContent(
                        state = currentState,
                        onItemClick = onItemClick,
                        onSearchValueChange = { carsListViewModel.searchCars(it) },
                        onFilterClick = carsListViewModel::openFilter,
                        baseUrl = carsListViewModel.baseUrl,
                    )
                }


            is CarsListState.Error -> ErrorMassage(
                message = currentState.reason,
                onRetry = carsListViewModel::loadData
            )
        }
    }
}

@Composable
fun ExitButton(
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(start = 8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.outline_close_24),

            contentDescription = "Назад",
            modifier = Modifier
                .size(30.dp)
        )
    }
}