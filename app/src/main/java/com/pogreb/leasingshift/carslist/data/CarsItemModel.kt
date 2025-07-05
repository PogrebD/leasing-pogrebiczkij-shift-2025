package com.pogreb.leasingshift.carslist.data

import com.pogreb.leasingshift.main.entity.Media
import com.pogreb.leasingshift.main.entity.enums.BodyType
import com.pogreb.leasingshift.main.entity.enums.Brand
import com.pogreb.leasingshift.main.entity.enums.Color
import com.pogreb.leasingshift.main.entity.enums.Steering
import com.pogreb.leasingshift.main.entity.enums.Transmission
import kotlinx.serialization.Serializable

@Serializable
data class CarsItemModel(
    val id: Long,
    val name: String,
    val brand: Brand,
    val media: List<Media>,
    val transmission: Transmission,
    val price: Long,
    val location: String,
    val color: Color,
    val bodyType: BodyType,
    val steering: Steering,
)