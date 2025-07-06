package com.pogreb.leasingshift.carslist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.carslist.domain.entity.CarsItem
import com.pogreb.leasingshift.carslist.presentation.SearchState
import com.pogreb.leasingshift.carslist.presentation.Status
import com.pogreb.leasingshift.formatCarName
import com.pogreb.leasingshift.main.entity.enums.Transmission
import com.pogreb.leasingshift.ui.theme.BorderExtralight
import com.pogreb.leasingshift.ui.theme.CustomTextStyle
import com.pogreb.leasingshift.ui.theme.TextQuartenery

const val days = 14

@Composable
fun CarsListContent(
    state: Status.Idle,
    onItemClick: (loanId: Long) -> Unit,
    onSearchValueChange: (String) -> Unit,
) {
    Search(
        text = state.searchState.query,
        onValueChange = onSearchValueChange,
    )

    when (val searchState = state.searchState) {
        is SearchState.Found -> CarsList(
            carsListItems = searchState.cars,
            onItemClick = onItemClick
        )

        is SearchState.NotFound -> NotFoundText()
    }
}

@Composable
private fun CarsList(
    carsListItems: List<CarsItem> = emptyList(),
    onItemClick: (loanId: Long) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        itemsIndexed(carsListItems) { index, item ->
            CarsListItem(
                item = item,
                onClick = onItemClick
            )
            if (index < carsListItems.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 1.dp,
                    color = BorderExtralight,
                )
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun CarsListItem(
    item: CarsItem,
    onClick: (loanId: Long) -> Unit,
) {

    val coverImageUrl = remember(item.media) {
        item.media.firstOrNull { it.isCover }?.url?.let {
            "https://shift-intensive.ru/api$it"
        }
    }

    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onClick(item.id) },
    ) {
        coverImageUrl?.let { url ->
            GlideImage(
                model = url,
                contentDescription = "Car image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(116.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            ) {
                it
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

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

@Composable
private fun Search(
    text: String = "",
    onValueChange: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
        Text(
            text = stringResource(R.string.search),
            style = CustomTextStyle.overInput,
            modifier = Modifier
                .height(20.dp),
        )
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(8.dp),
            label = {
                Text(
                    text = stringResource(R.string.search),
                    color = TextQuartenery
                )
            },

            )
    }
}

@Composable
private fun NotFoundText() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.search_not_found),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}