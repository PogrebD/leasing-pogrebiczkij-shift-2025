package com.pogreb.leasingshift.shared.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val url: String = "",
    val isCover: Boolean = false,
)