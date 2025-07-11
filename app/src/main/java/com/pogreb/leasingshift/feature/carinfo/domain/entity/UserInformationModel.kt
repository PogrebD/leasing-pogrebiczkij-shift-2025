package com.pogreb.leasingshift.feature.carinfo.domain.entity

data class UserInformationModel(
    val secondName: String = "",
    val firstName: String = "",
    val middleName: String = "",
    val dateOfBirth: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val comment: String = "",
)
