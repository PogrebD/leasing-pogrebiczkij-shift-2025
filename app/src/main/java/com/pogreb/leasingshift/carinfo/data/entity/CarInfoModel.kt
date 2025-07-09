package com.pogreb.leasingshift.carinfo.data.entity

import com.pogreb.leasingshift.main.domain.entity.Media
import com.pogreb.leasingshift.carslist.domain.enums.BodyType
import com.pogreb.leasingshift.carslist.domain.enums.Brand
import com.pogreb.leasingshift.carslist.domain.enums.Color
import com.pogreb.leasingshift.carslist.domain.enums.Steering
import com.pogreb.leasingshift.carslist.domain.enums.Transmission
import kotlinx.serialization.Serializable

@Serializable
data class CarInfoModel(
    val id: Long,
    val name: String,
    val brand: Brand,
    val media:  List<Media>,
    val transmission: Transmission,
    val price: Double,
    val location: String,
    val color: Color,
    val bodyType: BodyType,
    val steering: Steering,
    val rents: List<Rents>,
)
