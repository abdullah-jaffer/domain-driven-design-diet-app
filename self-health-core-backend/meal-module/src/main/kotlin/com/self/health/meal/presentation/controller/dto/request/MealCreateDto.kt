package com.self.health.meal.presentation.controller.dto.request

import com.self.health.meal.application.command.CreateMealCommand
import com.self.health.meal.application.command.CreateMealCommand.CreateMealItemCommand

data class MealCreateDto(val totalCalories: Double,
                         val localizedTime: String,
                         val mealItems: List<MealItemDto>) {

    data class MealItemDto(val calories: Double,
                           val carbohydrates: Double,
                           val protein: Double,
                           val fat: Double,
                           val sugar: Double,
                           val quantity: Long,
                           val unit: String)

    fun toCreateMealCommand(userId: String): CreateMealCommand {
        return CreateMealCommand(
            userId,
            this.localizedTime,
            this.totalCalories,
            this.mealItems.map {
            CreateMealItemCommand(
                it.calories,
                it.carbohydrates,
                it.protein,
                it.fat,
                it.sugar,
                it.quantity,
                it.unit)
            }
        )
    }
}