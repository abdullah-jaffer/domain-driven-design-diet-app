package com.self.health.food.domain.valueobject

import com.self.health.food.domain.enum.MeasurementUnit

data class Fats(val monoSaturatedFat: Double,
           val polySaturatedFat: Double,
           val saturatedFat: Double,
           val measurementUnit: MeasurementUnit = MeasurementUnit.GRAMS) {

    fun totalFat(): Double {
        return monoSaturatedFat + polySaturatedFat + saturatedFat
    }
}
