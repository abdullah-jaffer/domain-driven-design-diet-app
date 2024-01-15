package com.self.health.insights.infrastructure.repository.dao

import com.self.health.insights.domain.constant.MetricType
import org.springframework.data.mongodb.core.mapping.Document

@Document("diet_metrics")
data class DietMetrics(
    override var id: String?,
    val time: Time,
    val type: MetricType,
    val userId: String,
    var calorieIntake: List<CalorieIntake>): BaseEntity(id = id) {

    data class CalorieIntake(val energy: Energy,
                             val nutrients: Nutrient,
                             val timeStamp: String)

    data class Energy(val calories: Double)

    data class Nutrient(val fat: Double,
                        val carbohydrates: Double,
                        val protein: Double)

    data class Time(val year: Int,
                    val week: Int)

}
