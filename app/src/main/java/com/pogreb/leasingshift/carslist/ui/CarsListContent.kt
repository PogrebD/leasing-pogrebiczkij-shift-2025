package com.pogreb.leasingshift.carslist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.carslist.domain.entity.CarsItem
import com.pogreb.leasingshift.carslist.presentation.CarsListState
import com.pogreb.leasingshift.formatCarName
import com.pogreb.leasingshift.main.entity.enums.Transmission
import com.pogreb.leasingshift.ui.theme.CustomTextStyle

val days = 14

@Composable
fun CarsListContent(
    state: CarsListState.Content,
) {
    CarsList(
        carsItemListItems = state.cars
    )
}

@Composable
private fun CarsList(
    carsItemListItems: List<CarsItem> = emptyList()
) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(carsItemListItems) { item ->
            CarsListListItem(item = item)
        }
    }
}


@Composable
private fun CarsListListItem(item: CarsItem) {



    Row(
        Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)

    ) {


        Image(
            painter = painterResource(id = R.drawable.error_image),
            contentDescription = stringResource(id = R.string.error_title),
            modifier = Modifier
                .height(116.dp)
                .background(Color.Gray)
                .weight(1f)
                .clip(RoundedCornerShape(8.dp)),

            )

        Spacer(
            Modifier.width(
                24.dp
            )
        )

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = formatCarName(name = item.name, brand = item.brand),
                style = CustomTextStyle.primary,
                modifier = Modifier
                    .height(24.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(
                Modifier.height(
                    8.dp
                )
            )

            Text(
                text = when (item.transmission) {
                    Transmission.automatic -> "Автомат"
                    Transmission.manual -> "Механика"
                },
                style = CustomTextStyle.secondary,
                modifier = Modifier
                    .height(16.dp),
            )

            Spacer(
                Modifier.height(
                    26.dp
                )
            )

            Text(
                text = stringResource(
                    R.string.price,
                    item.price,
                ),
                style = CustomTextStyle.primary,
                modifier = Modifier
                    .height(24.dp),
            )

            Spacer(
                Modifier.height(
                    2.dp
                )
            )

            Text(
                text = stringResource(
                    R.string.price_for_amount_days,
                    item.price * days,
                    days
                ),
                style = CustomTextStyle.secondary,
                modifier = Modifier
                    .height(16.dp),
            )
        }
    }
}