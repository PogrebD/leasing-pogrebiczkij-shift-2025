package com.pogreb.leasingshift.carinfo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.carslist.domain.enums.BodyType
import com.pogreb.leasingshift.carslist.domain.enums.Color
import com.pogreb.leasingshift.carslist.domain.enums.Steering
import com.pogreb.leasingshift.carslist.domain.enums.Transmission

@Composable
fun Color.toText(): String = when (this) {
    Color.black -> stringResource(R.string.color_black)
    Color.white -> stringResource(R.string.color_white)
    Color.red -> stringResource(R.string.color_red)
    Color.silver -> stringResource(R.string.color_silver)
    Color.blue -> stringResource(R.string.color_blue)
    Color.grey -> stringResource(R.string.color_grey)
    Color.orange -> stringResource(R.string.color_orange)
}

@Composable
fun Steering.toText(): String = when (this) {
    Steering.left -> stringResource(R.string.steering_left)
    Steering.right -> stringResource(R.string.steering_right)
}

@Composable
fun Transmission.toText(): String = when (this) {
    Transmission.automatic -> stringResource(R.string.transmission_automatic)
    Transmission.manual -> stringResource(R.string.transmission_manual)
}

@Composable
fun BodyType.toText(): String = when (this) {
    BodyType.sedan -> stringResource(R.string.body_type_sedan)
    BodyType.suv -> stringResource(R.string.body_type_suv)
    BodyType.coupe -> stringResource(R.string.body_type_coupe)
    BodyType.hatchback -> stringResource(R.string.body_type_hatchback)
    BodyType.cabriolet -> stringResource(R.string.body_type_cabriolet)
}