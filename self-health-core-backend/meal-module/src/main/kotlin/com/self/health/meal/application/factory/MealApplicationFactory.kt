package com.self.health.meal.application.factory

import com.devskiller.friendly_id.FriendlyId
import com.self.health.meal.application.command.CreateMealCommand
import com.self.health.meal.domain.aggregate.Meal
import com.self.health.meal.domain.entity.MealItem
import com.self.health.meal.domain.enum.MeasurementUnit
import com.self.health.meal.domain.valueobject.Energy
import com.self.health.meal.domain.valueobject.FoodMeasurementUnit
import com.self.health.meal.domain.valueobject.Nutrients
import com.self.health.meal.domain.valueobject.Time
import org.springframework.stereotype.Component

@Component
class MealApplicationFactory {

    fun mealFrom(createMealCommand: CreateMealCommand): Meal {
        val mealId = FriendlyId.createFriendlyId()
        return Meal(
            mealId,
            Energy(createMealCommand.totalCalories),
            createMealCommand.userId,
            Time(createMealCommand.localizedTime),
            createMealCommand.mealItems.map {
            MealItem(
                null,
                Energy(it.calories),
                Nutrients(it.carbohydrates, it.protein, it.sugar),
                FoodMeasurementUnit(it.quantity, MeasurementUnit.valueOf(it.unit)),
                mealId
            )
        })

    }

}