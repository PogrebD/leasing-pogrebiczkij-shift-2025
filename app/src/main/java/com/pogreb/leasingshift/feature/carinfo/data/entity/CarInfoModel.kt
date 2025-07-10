package com.pogreb.leasingshift.feature.carinfo.data.entity

import com.pogreb.leasingshift.shared.domain.entity.Media
import com.pogreb.leasingshift.shared.domain.entity.BodyType
import com.pogreb.leasingshift.shared.domain.entity.Brand
import com.pogreb.leasingshift.shared.domain.entity.Color
import com.pogreb.leasingshift.shared.domain.entity.Steering
import com.pogreb.leasingshift.shared.domain.entity.Transmission
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
