package com.pogreb.leasingshift.main.entity.enums

import com.pogreb.leasingshift.R

enum class Color {
    black,
    white,
    red,
    silver,
    blue,
    grey,
    orange,
}

fun Color.getStringResourceId(): Int = when (this) {
    Color.black -> R.string.black
    Color.white -> R.string.white
    Color.red -> R.string.red
    Color.silver -> R.string.silver
    Color.blue -> R.string.blue
    Color.grey -> R.string.grey
    Color.orange -> R.string.orange
}