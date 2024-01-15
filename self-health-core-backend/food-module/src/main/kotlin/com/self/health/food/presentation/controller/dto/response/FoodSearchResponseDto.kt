package com.self.health.food.presentation.controller.dto.response

import com.self.health.food.domain.enum.MeasurementUnit

data class FoodSearchResponseDto(val id: String,
                                 val name: String,
                                 val calories: Double,
                                 val quantity: Long,
                                 val unit: MeasurementUnit,
                                 val image: String)
