package com.pogreb.leasingshift.feature.carinfo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.feature.carinfo.presentation.CarBookingState
import com.pogreb.leasingshift.feature.carinfo.presentation.CarInfoState
import com.pogreb.leasingshift.feature.carinfo.presentation.CarInfoViewModel
import com.pogreb.leasingshift.feature.carinfo.ui.booking.CarBooking
import com.pogreb.leasingshift.feature.carinfo.ui.booking.DataVerification
import com.pogreb.leasingshift.feature.carinfo.ui.booking.SuccessfulResult
import com.pogreb.leasingshift.feature.carinfo.ui.booking.UserInformation
import com.pogreb.leasingshift.main.ui.FullScreenProgressIndicator
import com.pogreb.leasingshift.shared.ui.ErrorMassage
import com.pogreb.leasingshift.shared.ui.components.Title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarInfoScreen(
    carInfoViewModel: CarInfoViewModel,
    onBackClick: () -> Unit,

    ) {
    val state by carInfoViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        carInfoViewModel.loadData()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (val currentState = state) {

            CarInfoState.Loading -> FullScreenProgressIndicator()

            is CarInfoState.Idle ->
                when (currentState.bookingState) {
                    CarBookingState.Content -> {
                        Row {
                            BackArrowButton(onBackClick)
                            Title(R.string.cars_title)
                        }
                        CarInfoContent(
                            car = currentState.car,
                            onReserveClick = carInfoViewModel::switchBookingState,
                            onBackClick = onBackClick,
                            viewModel = carInfoViewModel,
                            baseUrl = carInfoViewModel.baseUrl,
                        )
                    }

                    CarBookingState.Booking -> {
                        Row {
                            BackArrowButton(carInfoViewModel::switchBackBookingState)
                            Title(R.string.booking_title)
                        }
                        CarBooking(carInfoViewModel)
                    }

                    CarBookingState.UserInformation -> {
                        Row {
                            BackArrowButton(carInfoViewModel::switchBackBookingState)
                            Title(R.string.user_data_title)
                        }
                        UserInformation(carInfoViewModel)
                    }

                    CarBookingState.DataVerification -> {
                        Row {
                            ExitButton(carInfoViewModel::switchBackBookingState)
                            Title(R.string.data_verification_title)
                        }
                        DataVerification(carInfoViewModel)
                    }

                    CarBookingState.SuccessfulResult -> {
                        SuccessfulResult(carInfoViewModel)
                    }
                }


            is CarInfoState.Error -> ErrorMassage(
                message = currentState.reason,
                onRetry = {
                    carInfoViewModel.loadData()
                }
            )
        }
    }
}

@Composable
fun BackArrowButton(
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(start = 8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
            contentDescription = "Назад",
            modifier = Modifier
                .size(24.dp)
        )
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
            painter = painterResource(R.drawable.outline_light_close_24),
            contentDescription = "Назад",
            modifier = Modifier
                .size(30.dp)
        )
    }
}