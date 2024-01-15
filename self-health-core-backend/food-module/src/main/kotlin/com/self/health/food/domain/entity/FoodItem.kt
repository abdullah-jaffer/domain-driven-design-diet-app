package com.self.health.food.domain.entity

import com.self.health.food.domain.enum.FoodType
import com.self.health.food.domain.enum.ItemStatus
import com.self.health.food.domain.valueobject.Fats
import com.self.health.food.domain.valueobject.FoodDescription
import com.self.health.food.domain.valueobject.FoodMeasurementUnit
import com.self.health.food.domain.valueobject.MacroNutrients
import com.self.health.food.domain.valueobject.MicroNutrients

data class FoodItem(
    val id: String,
    val foodDescription: FoodDescription,
    val macroNutrients: MacroNutrients,
    val microNutrients: MicroNutrients,
    val fats: Fats,
    val foodMeasurementUnit: FoodMeasurementUnit,
    val foodType: FoodType,
    val categoryId: String,
    var status: ItemStatus,
    ) {

    fun calories(): Double {
        return ((4 * macroNutrients.sugar) + (4 * macroNutrients.carbohydrates) + (4 * macroNutrients.protein) + (9 * fats.totalFat())) * (foodMeasurementUnit.quantity/100)
    }
}