package com.pogreb.leasingshift.shared.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Brand {
    Haval,
    Hyundai,
    Volkswagen,
    Kia,
    Geely,
    Mercedes,
    @SerialName("Garden car")
    GardenCar,
    @SerialName("Grocery cart")
    GroceryCart,
    Haier,
    Invalid,
}