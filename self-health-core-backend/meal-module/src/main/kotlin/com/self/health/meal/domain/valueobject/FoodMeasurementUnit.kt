package com.self.health.meal.domain.valueobject

import com.self.health.meal.domain.enum.MeasurementUnit
import com.self.health.meal.domain.enum.MeasurementUnit.GRAMS

data class FoodMeasurementUnit(
    val quantity: Long,
    val unit: MeasurementUnit = GRAMS
)
