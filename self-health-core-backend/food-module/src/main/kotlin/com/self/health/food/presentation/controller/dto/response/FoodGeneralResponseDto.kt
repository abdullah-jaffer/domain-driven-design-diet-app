package com.self.health.food.presentation.controller.dto.response

data class FoodGeneralResponseDto(
    val id: String,
    val name: String,
    val quantity: Long,
    val unit: String,
    val image: String)