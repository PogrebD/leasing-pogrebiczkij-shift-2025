package com.pogreb.leasingshift.main.entity.enums

import com.pogreb.leasingshift.carslist.CarsListRoute
import com.pogreb.leasingshift.orders.OrdersRoute
import com.pogreb.leasingshift.profile.ProfileRoute
import kotlin.reflect.KClass

enum class NavigationOption(val route: KClass<*>) {
    CARSLIST(CarsListRoute::class),
    ORDERS(OrdersRoute::class),
    PROFILE(ProfileRoute::class),
}