package com.self.health.meal.application.command

data class CreateMealCommand(val userId: String,
                             val localizedTime: String,
                             val totalCalories: Double,
                             val mealItems: List<CreateMealItemCommand>) {

    data class CreateMealItemCommand(val calories: Double,
                                     val carbohydrates: Double,
                                     val protein: Double,
                                     val fat: Double,
                                     val sugar: Double,
                                     val quantity: Long,
                                     val unit: String)
}
