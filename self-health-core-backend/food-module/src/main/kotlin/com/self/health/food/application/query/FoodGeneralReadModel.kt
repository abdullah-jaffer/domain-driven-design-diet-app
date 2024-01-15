package com.self.health.food.application.query

data class FoodGeneralReadModel(
    val id: String,
    val name: String,
    val quantity: Long,
    val unit: String,
    val image: String)
