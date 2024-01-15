package com.self.health.meal.infrastructure.repository.repository.handler

import com.self.health.meal.domain.aggregate.Meal
import com.self.health.meal.infrastructure.factory.MealObjectFactory
import com.self.health.meal.infrastructure.repository.hibernate.repository.MealItemRepository
import com.self.health.meal.infrastructure.repository.hibernate.repository.MealRepository
import org.springframework.stereotype.Repository

@Repository
class MealRepositoryHandler(
    private val mealRepository: MealRepository,
    private val mealItemRepository: MealItemRepository,
    private val mealObjectFactory: MealObjectFactory):
    com.self.health.meal.application.repository.MealRepository {

    override fun saveMeal(meal: Meal) {
        val (mealDao, mealDaoItems) = mealObjectFactory.mealDaoFrom(meal)
        mealRepository.save(mealDao)
        mealItemRepository.saveAll(mealDaoItems)
    }

}