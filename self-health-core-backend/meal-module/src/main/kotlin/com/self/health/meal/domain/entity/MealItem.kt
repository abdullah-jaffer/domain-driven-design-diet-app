package com.self.health.meal.domain.entity

import com.self.health.meal.domain.valueobject.Energy
import com.self.health.meal.domain.valueobject.FoodMeasurementUnit
import com.self.health.meal.domain.valueobject.Nutrients

data class MealItem(val id: String?,
                    val energy: Energy,
                    val nutrients: Nutrients,
                    val measurement: FoodMeasurementUnit,
                    val mealId: String)
