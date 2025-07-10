package com.pogreb.leasingshift.feature.carslist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.shared.di.BaseUrl
import com.pogreb.leasingshift.feature.carslist.domain.entity.CarsItem
import com.pogreb.leasingshift.shared.domain.entity.Transmission
import com.pogreb.leasingshift.feature.carslist.presentation.CarsListState
import com.pogreb.leasingshift.feature.carslist.presentation.SearchState
import com.pogreb.leasingshift.ui.theme.BGPrimary
import com.pogreb.leasingshift.ui.theme.CustomTextStyle
import com.pogreb.leasingshift.ui.theme.IndicatorWhite
import com.pogreb.leasingshift.ui.theme.TextQuartenery
import com.pogreb.leasingshift.ui.theme.TextSecondary

const val days = 14

@Composable
fun CarsListContent(
    state: CarsListState.Idle,
    onItemClick: (loanId: Long) -> Unit,
    onSearchValueChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    @BaseUrl baseUrl: String,
) {
    Search(
        text = state.searchState.query,
        onValueChange = onSearchValueChange,
    )

    FilterButton(onFilterClick)

    when (val searchState = state.searchState) {
        is SearchState.Found -> CarsList(
            carsListItems = searchState.cars,
            onItemClick = onItemClick,
            baseUrl = baseUrl,
        )

        is SearchState.NotFound -> NotFoundText()
        is SearchState.SelectFilter -> {}
    }
}

@Composable
private fun CarsList(
    carsListItems: List<CarsItem> = emptyList(),
    onItemClick: (loanId: Long) -> Unit,
    @BaseUrl baseUrl: String,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 16.dp),

    ) {
        items(carsListItems) { item ->
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = BGPrimary,
                ),
                modifier = Modifier.padding(vertical = 2.dp)

            ){
                CarsListItem(
                    item = item,
                    onClick = onItemClick,
                    baseUrl = baseUrl,
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
    @BaseUrl baseUrl: String,
) {
    val coverImageUrl = item.media
        .firstOrNull { it.isCover }?.url?.let {
            baseUrl + it
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
                    .padding(end = 12.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit,
            ) {
                it
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = item.name,
                style = CustomTextStyle.primary,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = when (item.transmission) {
                    Transmission.automatic -> "Автомат"
                    Transmission.manual -> "Механика"
                    else -> ""
                },
                style = CustomTextStyle.secondary,
                modifier = Modifier
                    .padding(bottom = 26.dp),
            )

            Text(
                text = stringResource(
                    R.string.price,
                    item.price,
                ),
                style = CustomTextStyle.primary,
                modifier = Modifier
                    .padding(bottom = 2.dp),

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
            modifier = Modifier
                .fillMaxWidth(),
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
fun NotFoundText() {
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

@Composable
private fun FilterButton(onFilterClick: () -> Unit) {
    Button(
        onClick = { onFilterClick() },
        modifier = Modifier
            .height(56.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonColors(
            containerColor = TextSecondary,
            contentColor = IndicatorWhite,
            disabledContainerColor = TextQuartenery,
            disabledContentColor = IndicatorWhite,
        ),
    ) {
        Row {
            Icon(
                painter = painterResource(R.drawable.outline_filter_list_24),
                contentDescription = "filters button"
            )
        }
        Text(stringResource(R.string.filters_button))
    }
}