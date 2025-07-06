package com.pogreb.leasingshift.carinfo.domain.entity

import com.pogreb.leasingshift.carinfo.data.entity.Rents
import com.pogreb.leasingshift.main.entity.Media
import com.pogreb.leasingshift.main.entity.enums.BodyType
import com.pogreb.leasingshift.main.entity.enums.Brand
import com.pogreb.leasingshift.main.entity.enums.Color
import com.pogreb.leasingshift.main.entity.enums.Steering
import com.pogreb.leasingshift.main.entity.enums.Transmission

data class CarInfo(
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
val rents: Rents,
)
