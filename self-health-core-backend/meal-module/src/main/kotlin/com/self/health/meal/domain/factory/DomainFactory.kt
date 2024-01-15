package com.self.health.meal.domain.factory

import com.self.health.meal.domain.aggregate.Meal
import com.self.health.meal.domain.entity.MealItem
import com.self.health.meal.domain.event.MealEvent
import com.self.health.meal.domain.event.MealEvent.Payload
import com.self.health.meal.domain.event.constant.MealEventStatus.CREATED

class DomainFactory {

    companion object {
        fun orderCreatedDomainEventFrom(meal: Meal, mealItems: List<MealItem>): MealEvent {
            return MealEvent(CREATED, Payload(
                meal.id,
                meal.userId,
                meal.totalCalories.calories,
                meal.time.time,
                mealItems.map {
                    MealEvent.MealItem(
                        it.energy.calories,
                        it.nutrients.carbohydrates,
                        it.nutrients.protein,
                        it.nutrients.fat,
                        it.measurement.quantity,
                        it.measurement.unit.name)
                    }
                )
            )
        }
    }
}