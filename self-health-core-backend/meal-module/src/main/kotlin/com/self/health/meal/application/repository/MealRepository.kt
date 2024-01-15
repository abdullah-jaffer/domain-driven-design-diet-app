package com.self.health.meal.application.repository

import com.self.health.meal.domain.aggregate.Meal

interface MealRepository {
    fun saveMeal(meal: Meal)
}