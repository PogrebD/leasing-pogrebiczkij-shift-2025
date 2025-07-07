package com.pogreb.leasingshift.carinfo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.main.entity.enums.BodyType
import com.pogreb.leasingshift.main.entity.enums.Color
import com.pogreb.leasingshift.main.entity.enums.Steering
import com.pogreb.leasingshift.main.entity.enums.Transmission

@Composable
fun Color.getStringResource(): String = when (this) {
    Color.black -> stringResource(R.string.black)
    Color.white -> stringResource(R.string.white)
    Color.red -> stringResource(R.string.red)
    Color.silver -> stringResource(R.string.silver)
    Color.blue -> stringResource(R.string.blue)
    Color.grey -> stringResource(R.string.grey)
    Color.orange -> stringResource(R.string.orange)
}

@Composable
fun Steering.getStringResource(): String = when (this) {
    Steering.left -> stringResource(R.string.left)
    Steering.right -> stringResource(R.string.right)
}

@Composable
fun Transmission.getStringResource(): String = when (this) {
    Transmission.automatic -> stringResource(R.string.automatic)
    Transmission.manual -> stringResource(R.string.manual)
}

@Composable
fun BodyType.getStringResource(): String = when (this) {
    BodyType.sedan -> stringResource(R.string.sedan)
    BodyType.suv -> stringResource(R.string.suv)
    BodyType.coupe -> stringResource(R.string.coupe)
    BodyType.hatchback -> stringResource(R.string.hatchback)
    BodyType.cabriolet -> stringResource(R.string.cabriolet)
}