package com.pogreb.leasingshift.feature.carinfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.feature.carinfo.domain.entity.CarInfo
import com.pogreb.leasingshift.feature.carinfo.presentation.CarInfoViewModel
import com.pogreb.leasingshift.feature.carinfo.ui.components.Total
import com.pogreb.leasingshift.shared.di.BaseUrl
import com.pogreb.leasingshift.shared.domain.entity.DateBooking
import com.pogreb.leasingshift.shared.domain.entity.Media
import com.pogreb.leasingshift.shared.ui.toText
import com.pogreb.leasingshift.ui.theme.BorderExtralight
import com.pogreb.leasingshift.ui.theme.CustomTextStyle

@Composable
fun CarInfoContent(
    viewModel: CarInfoViewModel,
    car: CarInfo,
    onReserveClick: () -> Unit,
    onBackClick: () -> Unit,
    @BaseUrl baseUrl: String,
) {
    var bookingData by remember {
        mutableStateOf(viewModel.bookingData.value)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
    {
        Column(
            modifier = Modifier
                .weight(1F)
                .verticalScroll(rememberScrollState())
        )
        {
            ImageScroll(car.media, baseUrl)

            Characteristics(car)

            TotalPrice(bookingData.dateBooking, car.price)
        }

        BackButton(onBackClick)

        ReservationButton(onReserveClick)
    }
}

@Composable
fun BackButton(onBackClick: () -> Unit) {
    OutlinedButton(
        onClick = { onBackClick() },
        modifier = Modifier
            .padding(top = 16.dp)
            .height(56.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(stringResource(R.string.back_button))
    }
}

@Composable
fun ReservationButton(onReserveClick: () -> Unit) {
    Button(
        onClick = {
            onReserveClick()
        },
        modifier = Modifier
            .padding(top = 8.dp)
            .height(56.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(stringResource(R.string.reserve_button))
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageScroll(
    media: List<Media>,
    @BaseUrl baseUrl: String
) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(media) { item ->
            GlideImage(
                model = baseUrl + item.url,
                contentDescription = "Car image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .width(328.dp)
                    .padding(horizontal = 4.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillBounds
            ) {
                it
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
            }
        }
    }
}

@Composable
fun Characteristics(car: CarInfo) {
    Text(
        text = car.name,
        style = CustomTextStyle.appTitle,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 24.dp),
    )

    Text(
        text = stringResource(R.string.characteristics_title),
        style = CustomTextStyle.primary,
        modifier = Modifier
            .height(24.dp)
            .fillMaxWidth(),
    )

    TextPair(
        stringResource(R.string.transmission_heading),
        car.transmission.toText()
    )

    HorizontalDivider(
        thickness = 1.dp,
        color = BorderExtralight,
    )

    TextPair(
        stringResource(R.string.steering_heading),
        car.steering.toText()
    )

    HorizontalDivider(
        thickness = 1.dp,
        color = BorderExtralight,
    )

    TextPair(
        stringResource(R.string.body_type_heading),
        car.bodyType.toText()
    )

    HorizontalDivider(
        thickness = 1.dp,
        color = BorderExtralight,
    )

    TextPair(
        stringResource(R.string.color_heading),
        car.color.toText()
    )
}

@Composable
fun TextPair(label: String, value: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = label,
            style = CustomTextStyle.label,
            modifier = Modifier
                .weight(1f)
                .height(24.dp),
        )
        Text(
            text = value,
            style = CustomTextStyle.value,
            modifier = Modifier
                .weight(1f)
                .height(24.dp),
        )
    }
}

@Composable
fun TotalPrice(date: DateBooking, totalPrice: Long) {
    Text(
        text = stringResource(R.string.price),
        style = CustomTextStyle.primary,
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
    )

    HorizontalDivider(
        thickness = 1.dp,
        color = BorderExtralight,
        modifier = Modifier
            .padding(vertical = 16.dp),
    )

    Total(date, totalPrice)
}