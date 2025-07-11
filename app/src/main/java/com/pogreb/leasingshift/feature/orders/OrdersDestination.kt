package com.pogreb.leasingshift.feature.orders

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.orders() {
    composable<OrdersRoute> {
        OrdersScreen()
    }
}