package com.self.health.food.domain.valueobject

import com.self.health.food.domain.enum.MeasurementUnit
import com.self.health.food.domain.enum.MeasurementUnit.MILLIGRAMS

data class MicroNutrients(
    val cholesterol: Long,
    val unit: MeasurementUnit = MILLIGRAMS) {

}
