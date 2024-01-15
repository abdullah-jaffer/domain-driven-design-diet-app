package com.self.health.food.application.usecase

import com.self.health.food.application.command.SearchFoodsCommand
import com.self.health.food.application.query.FoodSearchReadModel

interface SearchFoodUseCase {
    fun handle(searchFoodsCommand: SearchFoodsCommand): List<FoodSearchReadModel>
}