package com.self.health.food.presentation.controller.dto.request

import com.self.health.food.application.command.CreateFoodItemCommand

data class FoodItemCreateDto(
    val description: String,
    val carbohydrates: Double,
    val protein: Double,
    val sugar: Double,
    val fat: Double,
    val quantity: Long,
    val categoryId: String,
    val image: String) {

    fun toFoodItemCreateCommand(userId: String): CreateFoodItemCommand {
        return CreateFoodItemCommand(
                userId,
                description,
                carbohydrates,
                protein,
                sugar,
                fat,
                quantity,
                categoryId,
                image)
    }
}