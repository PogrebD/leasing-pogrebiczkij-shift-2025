package com.pogreb.leasingshift.carslist.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.carinfo.ui.getStringResource
import com.pogreb.leasingshift.carslist.domain.entity.FilterParams
import com.pogreb.leasingshift.carslist.presentation.SearchState
import com.pogreb.leasingshift.ui.theme.BGPrimary
import com.pogreb.leasingshift.ui.theme.BorderLight
import com.pogreb.leasingshift.ui.theme.CustomTextStyle
import com.pogreb.leasingshift.ui.theme.TextPrimary
import com.pogreb.leasingshift.ui.theme.TextTertiary
import com.pogreb.leasingshift.utils.entity.BodyType
import com.pogreb.leasingshift.utils.entity.Brand
import com.pogreb.leasingshift.utils.entity.Color
import com.pogreb.leasingshift.utils.entity.Steering
import com.pogreb.leasingshift.utils.entity.Transmission

@Composable
fun FilterScreen(
    searchState: SearchState.SelectFilter,
    onBackClick: () -> Unit,
    onFilterSearchClick: (FilterParams) -> Unit
) {
    var selectedBrand by remember { mutableStateOf(searchState.brandName) }
    var selectedBodyType by remember { mutableStateOf(searchState.bodyTypeName) }
    var selectedSteering by remember { mutableStateOf(searchState.steeringName) }
    var selectedTransmission by remember { mutableStateOf(searchState.transmissionName) }
    var selectedColor by remember { mutableStateOf(searchState.colorName) }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedButton(
            onClick = { onBackClick() },
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(stringResource(R.string.back_button))
        }

        DropSelectionMenu(
            selectedValue = selectedBrand,
            label = stringResource(R.string.brand_label),
            hint = stringResource(R.string.brand_hint),
            data = getBrandStrings(),
            onValueChange = { selectedBrand = it }
        )

        DropSelectionMenu(
            selectedValue = selectedBodyType,
            label = stringResource(R.string.body_type_heading),
            hint = stringResource(R.string.body_type_hint),
            data = getBodyTypeStrings(),
            onValueChange = { selectedBodyType = it }
        )

        DropSelectionMenu(
            selectedValue = selectedSteering,
            label = stringResource(R.string.steering_heading),
            hint = stringResource(R.string.steering_hint),
            data = getSteeringStrings(),
            onValueChange = { selectedSteering = it }
        )

        DropSelectionMenu(
            selectedValue = selectedTransmission,
            label = stringResource(R.string.transmission_heading),
            hint = stringResource(R.string.transmission_hint),
            data = getTransmissionStrings(),
            onValueChange = { selectedTransmission = it }
        )

        DropSelectionMenu(
            selectedValue = selectedColor,
            label = stringResource(R.string.color_heading),
            hint = stringResource(R.string.color_hint),
            data = getColorStrings(),
            onValueChange = { selectedColor = it }
        )

        Button(
            onClick = {
                onFilterSearchClick(
                    FilterParams(
                        brandName = selectedBrand.takeIf { it.isNotEmpty() },
                        bodyTypeName = selectedBodyType.takeIf { it.isNotEmpty() },
                        steeringName = selectedSteering.takeIf { it.isNotEmpty() },
                        transmissionName = selectedTransmission.takeIf { it.isNotEmpty() },
                        colorName = selectedColor.takeIf { it.isNotEmpty() },
                    )
                )
            },
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),

            ) {
            Text(stringResource(R.string.filter_search_button))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropSelectionMenu(
    selectedValue: String,
    label: String,
    hint: String,
    data: List<String>,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Text(
        text = label,
        style = CustomTextStyle.overInput,
        modifier = Modifier
            .padding(vertical = 8.dp),
    )
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        brush = SolidColor(BorderLight)
                    ),
                    shape = RoundedCornerShape(16.dp),
                ),
            value = selectedValue.ifEmpty { hint },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                unfocusedTextColor = if (selectedValue.isEmpty()) TextTertiary else TextPrimary,
                focusedTextColor = if (selectedValue.isEmpty()) TextTertiary else TextPrimary,
                focusedContainerColor = BGPrimary,
                unfocusedContainerColor = BGPrimary,
                focusedIndicatorColor = BGPrimary,
                unfocusedIndicatorColor = BGPrimary,
            ),
            onValueChange = {},
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(BGPrimary)
        ) {
            data.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    },
                    modifier = Modifier.background(BGPrimary),
                )
            }
            DropdownMenuItem(
                text = { Text(text = "Не выбирать") },
                onClick = {
                    onValueChange("")
                    expanded = false
                },
                modifier = Modifier.background(BGPrimary),
            )

        }
    }
}

@SuppressLint("BuildListAdds")
@Composable
fun getBodyTypeStrings(): List<String> = buildList {
    BodyType.entries.forEach { it ->
        add(it.getStringResource())
    }
}

@SuppressLint("BuildListAdds")
@Composable
fun getBrandStrings(): List<String> = buildList {
    Brand.entries.forEach { it ->
        add(it.getText())
    }
}

@SuppressLint("BuildListAdds")
@Composable
fun getSteeringStrings(): List<String> = buildList {
    Steering.entries.forEach { it ->
        add(it.getStringResource())
    }
}

@SuppressLint("BuildListAdds")
@Composable
fun getTransmissionStrings(): List<String> = buildList {
    Transmission.entries.forEach { it ->
        add(it.getStringResource())
    }
}

@SuppressLint("BuildListAdds")
@Composable
fun getColorStrings(): List<String> = buildList {
    Color.entries.forEach { it ->
        add(it.getStringResource())
    }
}

@Composable
fun Brand.getText(): String = when (this) {
    Brand.Haval -> stringResource(R.string.brand_haval)
    Brand.Hyundai -> stringResource(R.string.brand_hyundai)
    Brand.Volkswagen -> stringResource(R.string.brand_volkswagen)
    Brand.Kia -> stringResource(R.string.brand_kia)
    Brand.Geely -> stringResource(R.string.brand_geely)
    Brand.Mercedes -> stringResource(R.string.brand_mercedes)
    Brand.GardenCar -> stringResource(R.string.brand_garden_car)
    Brand.GroceryCart -> stringResource(R.string.brand_grocery_cart)
    Brand.Haier -> stringResource(R.string.brand_haier)
    Brand.Invalid -> stringResource(R.string.brand_invalid)
}