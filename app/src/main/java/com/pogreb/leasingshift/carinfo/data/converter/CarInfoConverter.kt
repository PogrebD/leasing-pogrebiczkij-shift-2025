package com.pogreb.leasingshift.carinfo.data.converter

import com.pogreb.leasingshift.carinfo.data.entity.CarInfoModel
import com.pogreb.leasingshift.carinfo.domain.entity.CarInfo

class CarInfoConverter {

    fun convert(model: CarInfoModel): CarInfo =
        CarInfo(
            id = model.id,
            name = model.name,
            brand = model.brand,
            media = model.media,
            transmission = model.transmission,
            price = model.price,
            location = model.location,
            color = model.color,
            bodyType = model.bodyType,
            steering = model.steering,
            rents = model.rents,
        )
}