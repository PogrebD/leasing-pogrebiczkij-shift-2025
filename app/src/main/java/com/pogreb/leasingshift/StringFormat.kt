package com.pogreb.leasingshift

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pogreb.leasingshift.main.entity.enums.Brand

@Composable
fun formatCarName(name: String, brand: Brand): String =
    stringResource(id = R.string.car_name_and_brand, name, brand.name)

@Composable
fun formatUrlImage(url: String): String =
    stringResource(id = R.string.base_url_for_image, url)
