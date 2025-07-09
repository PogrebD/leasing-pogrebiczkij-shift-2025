package com.pogreb.leasingshift.carslist.domain

import android.content.Context
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.utils.entity.BodyType
import com.pogreb.leasingshift.utils.entity.Brand
import com.pogreb.leasingshift.utils.entity.Color
import com.pogreb.leasingshift.utils.entity.Steering
import com.pogreb.leasingshift.utils.entity.Transmission
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Mapper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun mapBodyType(value: String): BodyType {
        return when (value) {
            context.getString(R.string.body_type_sedan) -> BodyType.sedan
            context.getString(R.string.body_type_suv) -> BodyType.suv
            context.getString(R.string.body_type_coupe) -> BodyType.coupe
            context.getString(R.string.body_type_hatchback) -> BodyType.hatchback
            context.getString(R.string.body_type_cabriolet) -> BodyType.cabriolet
            else -> throw IllegalArgumentException("Unknown body type: '$value'")
        }
    }

    fun mapBrand(value: String): Brand {
        return when (value) {
            context.getString(R.string.brand_haval) -> Brand.Haval
            context.getString(R.string.brand_hyundai) -> Brand.Hyundai
            context.getString(R.string.brand_volkswagen) -> Brand.Volkswagen
            context.getString(R.string.brand_kia) -> Brand.Kia
            context.getString(R.string.brand_geely) -> Brand.Geely
            context.getString(R.string.brand_mercedes) -> Brand.Mercedes
            context.getString(R.string.brand_garden_car) -> Brand.GardenCar
            context.getString(R.string.brand_grocery_cart) -> Brand.GroceryCart
            context.getString(R.string.brand_haier) -> Brand.Haier
            context.getString(R.string.brand_invalid) -> Brand.Invalid
            else -> throw IllegalArgumentException("Unknown brand: '$value'")
        }
    }

    fun mapSteering(value: String): Steering {
        return when (value) {
            context.getString(R.string.steering_left) -> Steering.left
            context.getString(R.string.steering_right) -> Steering.right
            else -> throw IllegalArgumentException("Unknown steering: '$value'")
        }
    }

    fun mapTransmission(value: String): Transmission {
        return when (value) {
            context.getString(R.string.transmission_automatic) -> Transmission.automatic
            context.getString(R.string.transmission_manual) -> Transmission.manual
            else -> throw IllegalArgumentException("Unknown transmission: '$value'")
        }
    }
    fun mapColor(value: String): Color {
        return when (value) {
            context.getString(R.string.color_black) -> Color.black
            context.getString(R.string.color_white) -> Color.white
            context.getString(R.string.color_red) -> Color.red
            context.getString(R.string.color_silver) -> Color.silver
            context.getString(R.string.color_blue) -> Color.blue
            context.getString(R.string.color_grey) -> Color.grey
            context.getString(R.string.color_orange) -> Color.orange
            else -> throw IllegalArgumentException("Unknown color: '$value'")
        }
    }
}