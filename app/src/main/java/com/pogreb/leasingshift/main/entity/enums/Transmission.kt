package com.pogreb.leasingshift.main.entity.enums

import com.pogreb.leasingshift.R

enum class Transmission {
    automatic,
    manual
}
fun Transmission.getStringResourceId(): Int = when (this) {
    Transmission.automatic -> R.string.automatic
    Transmission.manual -> R.string.manual
}