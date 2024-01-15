package com.self.health.insights.domain.event

import com.self.health.insights.domain.constant.MealEventStatus


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

    fun totalCarbs() = this.payload.mealItems.sumOf { it.carbohydrates }
    fun totalFats()  = this.payload.mealItems.sumOf { it.fat }
    fun totalProtein() = this.payload.mealItems.sumOf { it.protein }

    data class MealItem(
        val calories: Double,
        val carbohydrates: Double,
        val protein: Double,
        val fat: Double,
        val quantity: Long,
        val unit: String
    )
}