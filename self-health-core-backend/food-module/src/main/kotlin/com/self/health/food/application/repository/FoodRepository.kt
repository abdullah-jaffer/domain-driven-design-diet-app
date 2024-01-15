package com.self.health.food.application.repository

import com.self.health.food.application.query.FoodGeneralReadModel
import com.self.health.food.domain.aggregate.UserFoodCatalog
import com.self.health.food.domain.entity.FoodItem

interface FoodRepository {
    fun getProductsBySearchKeyAndUser(
        searchKey: String,
        userId: String,
        page: Int,
        size: Int): List<FoodItem>

    fun findFoodItemsByUserId(userId: String): List<FoodGeneralReadModel>

    fun getUserFoodCatalogByUserId(userId: String): UserFoodCatalog

    fun saveUserFoodCatalog(userId: String, userFoodCatalog: UserFoodCatalog)
}