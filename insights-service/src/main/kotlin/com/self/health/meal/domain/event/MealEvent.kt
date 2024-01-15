package com.self.health.meal.domain.event;

import com.self.health.meal.domain.event.constant.MealEventStatus

data class MealEvent(val eventType: MealEventStatus,
                     val payload: Payload
) {

    data class Payload(
        val id: String,
        val userId: String,
        val totalCalories: Double,
        val localizedTime: String,
        val mealItems: List<MealItem>
    )

    data class MealItem(
        val calories: Double,
        val carbohydrates: Double,
        val protein: Double,
        val fat: Double,
        val quantity: Long,
        val unit: String
    )

}