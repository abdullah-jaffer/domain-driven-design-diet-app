package com.self.health.insights.domain.valueobject

data class CalorieIntake(val energy: Energy,
                         val nutrients: Nutrient,
                         val timeStamp: String)
