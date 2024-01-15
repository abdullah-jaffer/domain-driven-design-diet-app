package com.self.health.food.application.query

import com.self.health.food.domain.enum.MeasurementUnit

data class FoodSearchReadModel(
    val id: String,
    val name: String,
    val calories: Double,
    val quantity: Long,
    val unit: MeasurementUnit,
    val image: String)
