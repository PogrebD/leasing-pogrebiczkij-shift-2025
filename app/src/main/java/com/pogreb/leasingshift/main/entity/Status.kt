package com.pogreb.leasingshift.main.entity

sealed interface Status {
    data object Idle : Status
    data object Loading : Status
    data class Error(val reason: String) : Status
}