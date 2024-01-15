package com.self.health.insights.application.usecase

import com.self.health.meal.domain.event.MealEvent

interface MealCreatedUseCase {
    fun handle(mealEvent: MealEvent)
}