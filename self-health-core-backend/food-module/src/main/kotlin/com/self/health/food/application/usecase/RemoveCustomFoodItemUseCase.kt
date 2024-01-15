package com.self.health.food.application.usecase

import com.self.health.food.application.command.DeleteFoodItemCommand

interface RemoveCustomFoodItemUseCase {
    fun handle(deleteFoodItemCommand: DeleteFoodItemCommand)
}