package com.self.health.food.application.service

import com.self.health.food.application.query.CategoryReadModel
import com.self.health.food.application.repository.FoodCategoryRepository
import com.self.health.food.application.usecase.GetCategoriesUseCase
import org.springframework.stereotype.Service

@Service
class CategoryApplicationService(
    private val categoryRepository: FoodCategoryRepository):
    GetCategoriesUseCase {

    override fun handle(): List<CategoryReadModel> {
        return categoryRepository.getAllCategories()
    }


}