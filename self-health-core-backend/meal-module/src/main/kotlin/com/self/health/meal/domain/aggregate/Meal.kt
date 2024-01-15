package com.self.health.meal.domain.aggregate

import com.self.health.meal.domain.entity.MealItem
import com.self.health.meal.domain.exception.CaloriesDoNotMatchException
import com.self.health.meal.domain.exception.EmptyMealItemsListException
import com.self.health.meal.domain.factory.DomainFactory
import com.self.health.meal.domain.valueobject.Energy
import com.self.health.meal.domain.valueobject.Time

data class Meal(
    val id: String,
    val totalCalories: Energy,
    val userId: String,
    val time: Time,
    var mealItems: List<MealItem>
    ): BaseAggregate() {
    init {
        shouldHaveMealItems(this.userId)
        itemCaloriesShouldMatchTotalCalories(this.userId)

        this.registerEvent(DomainFactory.orderCreatedDomainEventFrom(this, mealItems))
    }

    fun mealItems(): List<MealItem> {
        return mealItems
    }

    private fun itemCaloriesShouldMatchTotalCalories(userId: String) {
        val actualTotalCalories = mealItems.sumOf { it.energy.calories }
        if (actualTotalCalories != this.totalCalories.calories) {
            throw CaloriesDoNotMatchException(userId)
        }
    }

    private fun shouldHaveMealItems(userId: String) {
        if (mealItems.isEmpty()) {
            throw EmptyMealItemsListException(userId)
        }
    }
}