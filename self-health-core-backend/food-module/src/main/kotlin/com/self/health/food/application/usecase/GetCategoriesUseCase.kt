package com.self.health.food.application.usecase

import com.self.health.food.application.query.CategoryReadModel

interface GetCategoriesUseCase {
    fun handle(): List<CategoryReadModel>
}