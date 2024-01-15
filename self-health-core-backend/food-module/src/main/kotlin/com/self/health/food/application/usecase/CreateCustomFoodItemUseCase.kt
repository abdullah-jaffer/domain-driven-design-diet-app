package com.self.health.food.application.usecase

import com.self.health.food.application.command.CreateFoodItemCommand

interface CreateCustomFoodItemUseCase {
    fun handle(createFoodItemCommand: CreateFoodItemCommand)

}