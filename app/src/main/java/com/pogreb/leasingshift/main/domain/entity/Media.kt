package com.pogreb.leasingshift.main.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val url: String = "",
    val isCover: Boolean = false,
)