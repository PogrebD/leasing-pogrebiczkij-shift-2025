package com.pogreb.leasingshift.main.entity.enums

import com.pogreb.leasingshift.R

enum class BodyType {
    sedan,
    suv,
    coupe,
    hatchback,
    cabriolet,
}

fun BodyType.getStringResourceId(): Int = when (this) {
    BodyType.sedan -> R.string.sedan
    BodyType.suv -> R.string.suv
    BodyType.coupe -> R.string.coupe
    BodyType.hatchback -> R.string.hatchback
    BodyType.cabriolet -> R.string.cabriolet
}
