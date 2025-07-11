package com.pogreb.leasingshift.feature.carinfo.ui.booking

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
import com.pogreb.leasingshift.feature.carinfo.ui.components.NextButton
import com.pogreb.leasingshift.feature.carinfo.ui.components.ResultText
import com.pogreb.leasingshift.feature.carinfo.ui.components.Total
import com.pogreb.leasingshift.ui.theme.BGSecondary
import com.pogreb.leasingshift.ui.theme.CustomTextStyle
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun DataVerification(
    viewModel: CarInfoViewModel
) {
    var bookingData by remember {
        mutableStateOf(viewModel.bookingData.value)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f),

            ) {
            BookingData(viewModel)

            UserData(viewModel)
        }
        Spacer(Modifier.size(24.dp))

        Total(
            date = bookingData.dateBooking,
            totalPrice = bookingData.price,
        )

        NextButton(
            text = stringResource(R.string.complete_button),
            onClick = viewModel::switchBookingState,
        )
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
private fun BookingData(viewModel: CarInfoViewModel) {
    val formattedStartDate =
        SimpleDateFormat(
            "dd.mm.yyyy",
            Locale.getDefault()
        ).format(viewModel.bookingData.value.dateBooking.startDate)
    val formattedEndDate =
        SimpleDateFormat(
            "dd.mm.yyyy",
            Locale.getDefault()
        ).format(viewModel.bookingData.value.dateBooking.endDate)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = BGSecondary,
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.booking_data_label),
            modifier = Modifier
                .padding(top = 12.dp, start = 16.dp, bottom = 8.dp),
            style = CustomTextStyle.primary
        )

        ResultText(stringResource(R.string.car_label), viewModel.bookingData.value.carName.ifEmpty {
            stringResource(
                R.string.information_missing
            )
        })

        ResultText(
            stringResource(R.string.boking_dates_lable),
            stringResource(R.string.date_format_result, formattedStartDate, formattedEndDate)

        )

        ResultText(
            stringResource(R.string.place_receiving_label),
            viewModel.bookingData.value.placeReceiving.ifEmpty {
                stringResource(
                    R.string.information_missing
                )
            }
        )

        ResultText(
            stringResource(R.string.place_return_label),
            viewModel.bookingData.value.placeReturn.ifEmpty {
                stringResource(
                    R.string.information_missing
                )
            }
        )
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
private fun UserData(viewModel: CarInfoViewModel) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = BGSecondary,
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.client_data_label),
            modifier = Modifier
                .padding(top = 24.dp, start = 16.dp, bottom = 8.dp),
            style = CustomTextStyle.primary
        )

        ResultText(
            stringResource(R.string.fio_label),
            if (viewModel.userInfoData.value.secondName.isEmpty()
                && viewModel.userInfoData.value.firstName.isEmpty()
                && viewModel.userInfoData.value.middleName.isEmpty()
            )
                stringResource(
                    R.string.information_missing
                )
            else
                stringResource(
                    R.string.fio,
                    viewModel.userInfoData.value.secondName,
                    viewModel.userInfoData.value.firstName,
                    viewModel.userInfoData.value.middleName
                )
        )

        ResultText(
            stringResource(R.string.dob_label),
            viewModel.userInfoData.value.dateOfBirth.ifEmpty {
                stringResource(
                    R.string.information_missing
                )
            }
        )

        ResultText(
            stringResource(R.string.phone_label),
            viewModel.userInfoData.value.phoneNumber.ifEmpty {
                stringResource(
                    R.string.information_missing
                )
            }
        )

        ResultText(
            stringResource(R.string.email_label),
            viewModel.userInfoData.value.email.ifEmpty {
                stringResource(
                    R.string.information_missing
                )
            }
        )

        ResultText(
            stringResource(R.string.comment_label),
            viewModel.userInfoData.value.comment.ifEmpty {
                stringResource(
                    R.string.information_missing
                )
            }
        )
    }
}