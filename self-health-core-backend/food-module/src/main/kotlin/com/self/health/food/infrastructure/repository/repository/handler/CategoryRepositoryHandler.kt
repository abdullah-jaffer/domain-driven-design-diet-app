package com.self.health.food.infrastructure.repository.repository.handler

import com.self.health.food.application.query.CategoryReadModel
import com.self.health.food.application.repository.FoodCategoryRepository
import com.self.health.food.infrastructure.factory.CategoryObjectFactory
import com.self.health.food.infrastructure.repository.hibernate.repository.CategoryRepository
import org.springframework.stereotype.Repository

@Repository
class CategoryRepositoryHandler(
    private val categoryRepository: CategoryRepository,
    private  val categoryObjectFactory: CategoryObjectFactory
): FoodCategoryRepository {

    override fun getAllCategories(): List<CategoryReadModel> {
        return categoryObjectFactory.categoryReadModelFrom(categoryRepository.findAll())
    }
}