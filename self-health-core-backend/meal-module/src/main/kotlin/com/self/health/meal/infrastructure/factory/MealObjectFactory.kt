package com.self.health.meal.infrastructure.factory

import com.self.health.meal.domain.aggregate.Meal
import com.self.health.meal.domain.entity.MealItem
import com.self.health.meal.infrastructure.repository.dao.MealDAO
import com.self.health.meal.infrastructure.repository.dao.MealItemDAO
import org.springframework.stereotype.Component

@Component
class MealObjectFactory {
    fun mealDaoFrom(meal: Meal): Pair<MealDAO, List<MealItemDAO>> {
        val mealDAOItems = mealItemDaoListFrom(meal.mealItems())

        return Pair(MealDAO(meal.id, meal.totalCalories.calories, meal.userId, meal.time.time), mealDAOItems)
    }

    fun mealItemDaoListFrom(mealItems: List<MealItem>): List<MealItemDAO>{
        return mealItems.map {
            MealItemDAO(
                it.id,
                it.energy.calories,
                it.nutrients.carbohydrates,
                it.nutrients.protein,
                it.nutrients.fat,
                it.measurement.quantity,
                it.measurement.unit.name,
                it.mealId)
        }
    }

}