package com.pogreb.leasingshift.carslist.data.converter

import com.pogreb.leasingshift.carslist.data.entity.CarsItemModel
import com.pogreb.leasingshift.carslist.domain.entity.CarsItem


class CarsItemConverter {

	fun convert(model: CarsItemModel): CarsItem =
		CarsItem(
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
        )
}