package com.pogreb.leasingshift.carslist.domain.entity

import com.pogreb.leasingshift.main.entity.Media
import com.pogreb.leasingshift.main.entity.enums.Brand
import com.pogreb.leasingshift.main.entity.enums.Transmission

data class CarsItem(
    val id: Long,
    val name: String,
    val brand: Brand,
    val media: List<Media>,
    val transmission: Transmission,
    val price: Long,
)