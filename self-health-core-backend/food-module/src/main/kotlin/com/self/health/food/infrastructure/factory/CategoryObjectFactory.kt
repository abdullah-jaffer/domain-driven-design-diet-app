package com.self.health.food.infrastructure.factory

import com.self.health.food.application.query.CategoryReadModel
import com.self.health.food.infrastructure.repository.dao.CategoryDAO
import org.springframework.stereotype.Component

@Component
class CategoryObjectFactory {

    fun categoryReadModelFrom(categoriesFromDb: List<CategoryDAO>): List<CategoryReadModel> {
        return categoriesFromDb.map {
            CategoryReadModel(
                it.id,
                it.name
            )
        }
    }
}