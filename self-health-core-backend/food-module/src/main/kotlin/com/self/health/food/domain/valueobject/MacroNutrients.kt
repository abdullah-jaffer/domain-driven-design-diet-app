package com.self.health.food.domain.valueobject

import com.self.health.food.domain.enum.MeasurementUnit

data class MacroNutrients(
    val carbohydrates: Double,
    val protein: Double,
    val sugar: Double,
    val measurementUnit: MeasurementUnit = MeasurementUnit.GRAMS)
