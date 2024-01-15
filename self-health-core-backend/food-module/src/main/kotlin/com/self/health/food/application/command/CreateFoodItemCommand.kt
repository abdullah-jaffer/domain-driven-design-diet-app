package com.self.health.food.application.command

data class CreateFoodItemCommand(val userId:String,
                                 val description: String,
                                 val carbohydrates: Double,
                                 val protein: Double,
                                 val sugar: Double,
                                 val fat: Double,
                                 val quantity: Long,
                                 val categoryId: String,
                                 val image: String)
