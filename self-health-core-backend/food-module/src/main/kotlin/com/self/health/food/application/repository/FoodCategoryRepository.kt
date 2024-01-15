package com.self.health.food.application.repository

import com.self.health.food.application.query.CategoryReadModel

interface FoodCategoryRepository {
    fun getAllCategories(): List<CategoryReadModel>
}