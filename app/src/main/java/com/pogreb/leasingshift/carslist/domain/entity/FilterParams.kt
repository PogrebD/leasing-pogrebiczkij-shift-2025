package com.pogreb.leasingshift.carslist.domain.entity

import com.pogreb.leasingshift.utils.entity.BodyType
import com.pogreb.leasingshift.utils.entity.Brand


data class FilterParams(
    val brandName: String?,
    val bodyTypeName: String?,
    val steeringName: String?,
    val transmissionName: String?,
    val colorName: String?,
)
