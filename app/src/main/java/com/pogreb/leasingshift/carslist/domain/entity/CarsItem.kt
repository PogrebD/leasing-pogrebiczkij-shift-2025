package com.pogreb.leasingshift.carslist.domain.entity

import com.pogreb.leasingshift.main.domain.entity.Media
import com.pogreb.leasingshift.utils.entity.Brand
import com.pogreb.leasingshift.utils.entity.Transmission

data class CarsItem(
    val id: Long,
    val name: String,
    val brand: Brand,
    val media: List<Media>,
    val transmission: Transmission,
    val price: Long,
)