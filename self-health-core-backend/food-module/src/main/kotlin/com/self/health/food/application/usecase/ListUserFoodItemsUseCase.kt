package com.self.health.food.application.usecase

import com.self.health.food.application.command.FetchUserFoodsCommand
import com.self.health.food.application.query.FoodGeneralReadModel

interface ListUserFoodItemsUseCase {
    fun handle(fetchUserFoodsCommand: FetchUserFoodsCommand): List<FoodGeneralReadModel>
}