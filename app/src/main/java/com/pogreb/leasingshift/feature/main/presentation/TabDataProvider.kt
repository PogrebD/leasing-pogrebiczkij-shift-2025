package com.pogreb.leasingshift.feature.main.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.feature.carslist.CarsListRoute
import com.pogreb.leasingshift.feature.main.domain.entity.NavigationOption
import com.pogreb.leasingshift.feature.main.domain.entity.TabItem
import com.pogreb.leasingshift.feature.orders.OrdersRoute
import com.pogreb.leasingshift.feature.profile.ProfileRoute

object TabDataProvider {
    fun getTabs(): List<TabItem> = listOf(
        TabItem(
            option = NavigationOption.CARSLIST,
            icon = Icons.Default.ShoppingCart,
            labelRes = R.string.cars_title
        ),
        TabItem(
            option = NavigationOption.ORDERS,
            icon = Icons.Default.Favorite,
            labelRes = R.string.orders_title
        ),
        TabItem(
            option = NavigationOption.PROFILE,
            icon = Icons.Default.AccountCircle,
            labelRes = R.string.profile_title
        )
    )

    fun getRouteMap():Map<NavigationOption, Any> =
        mapOf(
            NavigationOption.CARSLIST to CarsListRoute,
            NavigationOption.ORDERS to OrdersRoute,
            NavigationOption.PROFILE to ProfileRoute
        )
}