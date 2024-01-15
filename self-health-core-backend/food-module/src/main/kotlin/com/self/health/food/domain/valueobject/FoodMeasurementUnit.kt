package com.self.health.food.domain.valueobject

import com.self.health.food.domain.enum.MeasurementUnit
import com.self.health.food.domain.enum.MeasurementUnit.GRAMS

data class FoodMeasurementUnit(
    val quantity: Long,
    val unit: MeasurementUnit = GRAMS)
