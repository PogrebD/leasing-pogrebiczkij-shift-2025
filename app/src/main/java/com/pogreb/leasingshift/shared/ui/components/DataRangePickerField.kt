package com.pogreb.leasingshift.shared.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.shared.domain.entity.DateBooking
import com.pogreb.leasingshift.ui.theme.BGSecondary
import com.pogreb.leasingshift.ui.theme.BGTertiary
import com.pogreb.leasingshift.ui.theme.BrandExtralight
import com.pogreb.leasingshift.ui.theme.CustomTextStyle
import com.pogreb.leasingshift.ui.theme.TextPrimary
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerField(
    date: DateBooking,
    onSelected: (DateBooking) -> Unit,
) {
    var selectedDate by remember {
        mutableStateOf<Pair<Long?, Long?>>(
            Pair(
                date.startDate,
                date.endDate
            )
        )
    }
    var showModal by remember { mutableStateOf(false) }
    var numberOfDays: Long by remember { mutableLongStateOf(date.numDays) }

    val formattedFirstDate =
        SimpleDateFormat("dd", Locale.getDefault()).format(selectedDate.first)
    val formattedSecondDate =
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(selectedDate.second)

    Field(
        value = stringResource(
            R.string.date_format,
            formattedFirstDate,
            formattedSecondDate,
            numberOfDays
        ),
        color =
            TextPrimary

    ) { showModal = true }

    if (showModal) {
        DateRangePickerModal(
            onDateRangeSelected = {
                if (it.second != null && it.first != null) {
                    selectedDate = it
                    numberOfDays = Duration.between(
                        Instant.ofEpochMilli(selectedDate.first!!),
                        Instant.ofEpochMilli(selectedDate.second!!)
                    ).toDays()
                    onSelected(
                        DateBooking(
                            selectedDate.first!!,
                            selectedDate.second!!,
                            numberOfDays
                        )
                    )
                    showModal = false
                }
            },
            onDismiss = { showModal = false }
        )
    }
}

@Composable
fun formatDateBooking(date: DateBooking): String {
    val formattedFirstDate =
        SimpleDateFormat("dd", Locale.getDefault()).format(date.startDate)
    val formattedSecondDate =
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date.endDate)
    return stringResource(
        R.string.date_format,
        formattedFirstDate,
        formattedSecondDate,
        date.numDays
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {
    val state = rememberDateRangePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            state.selectedStartDateMillis,
                            state.selectedEndDateMillis
                        )
                    )
                }
            ) {
                Text(stringResource(R.string.select_button), color = TextPrimary)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel_button), color = TextPrimary)
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = BGTertiary,
        )
    ) {
        DateRangePicker(
            state = state,
            headline = {
                Text(
                    text = stringResource(R.string.select_dates_hint),
                    modifier = Modifier.padding(start = 16.dp)
                )
            },
            title = {},
            showModeToggle = false,
            modifier = Modifier
                .fillMaxWidth(),
            colors = DatePickerDefaults.colors(
                containerColor = BGSecondary,
                dayInSelectionRangeContainerColor = BrandExtralight,
            )
        )
    }
}

@Composable
private fun Field(
    value: String,
    color: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
        Text(
            text = stringResource(R.string.boking_dates_lable),
            style = CustomTextStyle.overInput,
            modifier = Modifier
                .height(20.dp)

        )
        OutlinedTextField(
            value = value,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick),
            onValueChange = {},
            readOnly = true,
            enabled = false,
            shape = RoundedCornerShape(8.dp),
            placeholder = {
                Text(
                    text = stringResource(R.string.boking_dates_lable),
                    color = color
                )
            },
        )
    }
}