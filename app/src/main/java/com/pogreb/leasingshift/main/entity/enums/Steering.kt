package com.pogreb.leasingshift.main.entity.enums

import com.pogreb.leasingshift.R

enum class Steering {
    left,
    right,
}

fun Steering.getStringResourceId(): Int = when (this) {
    Steering.left -> R.string.left
    Steering.right -> R.string.right
}