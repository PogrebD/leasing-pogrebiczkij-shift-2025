package com.pogreb.leasingshift.carinfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.carinfo.domain.entity.CarInfo
import com.pogreb.leasingshift.formatCarName
import com.pogreb.leasingshift.main.domain.entity.Media
import com.pogreb.leasingshift.main.ui.Total
import com.pogreb.leasingshift.ui.theme.BorderExtralight
import com.pogreb.leasingshift.ui.theme.CustomTextStyle

@Composable
fun CarInfoContent(
    car: CarInfo,
    onReserveClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    Column()
    {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        {
            item {
                ImageScroll(car.media)

                Characteristics(car)

                Spacer(modifier = Modifier.height(24.dp))

                TotalPrice(car.price)
            }

        }

        Buttons(onBackClick, onReserveClick)
    }
}

@Composable
fun Buttons(onBackClick: () -> Unit, onReserveClick: () -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))

    BackButton(onBackClick)

    Spacer(modifier = Modifier.height(8.dp))

    ReservationButton(onReserveClick)

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun BackButton(onBackClick: () -> Unit) {
    OutlinedButton(
        onClick = { onBackClick() },
        modifier = Modifier
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
        onClick = { onReserveClick() },
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),

        ) {
        Text(stringResource(R.string.reserve_button))
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageScroll(media: List<Media>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(media) { item ->
            GlideImage(
                model = "https://shift-intensive.ru/api" + item.url,
                contentDescription = "Car image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .width(328.dp)
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
        text = formatCarName(name = car.name, brand = car.brand),
        style = CustomTextStyle.appTitle,
        modifier = Modifier
            .height(32.dp)
            .fillMaxWidth(),
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = stringResource(R.string.characteristics_title),
        style = CustomTextStyle.primary,
        modifier = Modifier
            .height(24.dp)
            .fillMaxWidth(),
    )

    TextPair(
        stringResource(R.string.transmission_heading),
        car.transmission.getStringResource()
    )

    HorizontalDivider(
        thickness = 1.dp,
        color = BorderExtralight,
    )

    TextPair(
        stringResource(R.string.steering_heading),
        car.steering.getStringResource()
    )

    HorizontalDivider(
        thickness = 1.dp,
        color = BorderExtralight,
    )

    TextPair(
        stringResource(R.string.body_type_heading),
        car.bodyType.getStringResource()
    )

    HorizontalDivider(
        thickness = 1.dp,
        color = BorderExtralight,
    )

    TextPair(
        stringResource(R.string.color_heading),
        car.color.getStringResource()
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
fun TotalPrice(totalPrice: Double) {
    Text(
        text = stringResource(R.string.price),
        style = CustomTextStyle.primary,
        modifier = Modifier
            .height(24.dp)
            .fillMaxWidth(),
    )

    HorizontalDivider(
        thickness = 1.dp,
        color = BorderExtralight,
        modifier = Modifier
            .padding(vertical = 16.dp),
    )

    Total(totalPrice)
}