package com.pogreb.leasingshift.carslist.domain.entity

import com.pogreb.leasingshift.main.domain.entity.Media
import com.pogreb.leasingshift.utils.entity.BodyType
import com.pogreb.leasingshift.utils.entity.Brand
import com.pogreb.leasingshift.utils.entity.Color
import com.pogreb.leasingshift.utils.entity.Steering
import com.pogreb.leasingshift.utils.entity.Transmission

data class CarsItem(
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