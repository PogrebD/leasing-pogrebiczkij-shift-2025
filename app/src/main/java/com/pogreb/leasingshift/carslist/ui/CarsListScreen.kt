package com.pogreb.leasingshift.carslist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.carslist.domain.entity.CarsItem
import com.pogreb.leasingshift.carslist.presentation.CarsListState
import kotlinx.coroutines.launch
import com.pogreb.leasingshift.network.NetworkModule
import com.pogreb.leasingshift.ui.theme.CustomTextStyle
import kotlin.collections.map
import kotlin.text.orEmpty


@Composable
fun CarsListScreen(modifier: Modifier = Modifier) {
    var state: CarsListState by remember { mutableStateOf(CarsListState.Loading) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        state = CarsListState.Loading
        try {
            val carsItemModels = getCarsItemModels()
            state = CarsListState.Content(
                cars = carsItemModels,
            )
        } catch (e: Exception) {
            state = CarsListState.Error(message = e.message.orEmpty())
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Title()

        when (val currentState = state) {

            CarsListState.Loading -> FullScreenProgressIndicator()

            is CarsListState.Content -> CarsListContent(
                state = currentState,
            )

            is CarsListState.Error -> CarsListError(
                message = currentState.message,
                onRetry = {
                    coroutineScope.launch {
                        state = CarsListState.Loading
                        try {
                            val CarsListItems = getCarsItemModels()
                            state = CarsListState.Content(
                                cars = CarsListItems,
                            )
                        } catch (e: Exception) {
                            state = CarsListState.Error(message = e.message.orEmpty())
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(R.string.cars),
        style = CustomTextStyle.appTitle,
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .height(56.dp),
    )
}

private suspend fun getCarsItemModels(): List<CarsItem> {
    val CarsItemModels = NetworkModule.carsApi.getAllCars()
    return CarsItemModels.data.map(NetworkModule.carsItemConverter::convert)
}
