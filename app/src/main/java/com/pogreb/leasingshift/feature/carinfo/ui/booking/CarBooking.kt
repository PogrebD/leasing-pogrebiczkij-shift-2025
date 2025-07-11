package com.pogreb.leasingshift.feature.carinfo.ui.booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.feature.carinfo.presentation.CarInfoViewModel
import com.pogreb.leasingshift.feature.carinfo.ui.components.InputField
import com.pogreb.leasingshift.feature.carinfo.ui.components.NextButton
import com.pogreb.leasingshift.shared.ui.components.DateRangePickerField

@Composable
fun CarBooking(
    viewModel: CarInfoViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
    ) {

        var bookingData by remember {
            mutableStateOf(viewModel.bookingData.value)
        }

        Column(
            modifier = Modifier
                .weight(1F)
        )
        {
            DateRangePickerField(
                bookingData.dateBooking,
                { bookingData = bookingData.copy(dateBooking = it) })

            InputField(
                text = stringResource(R.string.place_receiving_label),
                value = bookingData.placeReceiving,
                onValueChange = { value -> bookingData = bookingData.copy(placeReceiving = value) },
            )

            InputField(
                text = stringResource(R.string.place_return_label),
                value = bookingData.placeReturn,
                onValueChange = { value -> bookingData = bookingData.copy(placeReturn = value) },
            )
        }

        NextButton(
            text = stringResource(R.string.next_button),
            onClick = {
                viewModel.switchBookingState()
                viewModel.updateBookingData(bookingData)
            },
        )
    }
}