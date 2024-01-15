package com.self.health.food.domain.aggregate

import com.self.health.food.domain.entity.FoodItem
import com.self.health.food.domain.enum.ItemStatus.INACTIVE
import com.self.health.food.domain.exception.FoodItemToDeleteNotFoundException
import com.self.health.food.domain.exception.ReachedFoodLimitException

data class UserFoodCatalog(
    private var userId: String,
    private var foodItems: List<FoodItem>) {

    companion object {
        const val currentMealLimit = 10
    }
    fun addFoodItem(foodItem: FoodItem) {
        if(foodItems.size >= currentMealLimit) {
            throw ReachedFoodLimitException()
        }

        val mutableFoodItems = foodItems.toMutableList()
        mutableFoodItems.add(foodItem)
        foodItems = mutableFoodItems.toList()
    }

    fun removeFoodItem(foodItemId: String) {
        val foodItemToDelete = foodItems.firstOrNull { it.id == foodItemId } ?: throw FoodItemToDeleteNotFoundException(foodItemId)

        foodItems = foodItems.filter { it.id != foodItemId }
        foodItemToDelete.status = INACTIVE
        val mutableFoodItems = foodItems.toMutableList()
        mutableFoodItems.add(foodItemToDelete)
        foodItems = mutableFoodItems.toList()
    }

    fun foodItems() : List<FoodItem> {
        return foodItems
    }
}
