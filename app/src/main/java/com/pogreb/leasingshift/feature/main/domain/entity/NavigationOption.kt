package com.pogreb.leasingshift.feature.main.domain.entity

import com.pogreb.leasingshift.feature.carslist.CarsListRoute
import com.pogreb.leasingshift.feature.orders.OrdersRoute
import com.pogreb.leasingshift.feature.profile.ProfileRoute
import kotlin.reflect.KClass

enum class NavigationOption(val route: KClass<*>) {
    CARSLIST(CarsListRoute::class),
    ORDERS(OrdersRoute::class),
    PROFILE(ProfileRoute::class),
}