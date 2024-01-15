package com.self.health.insights.domain.valueobject

data class MetricsDashboard(
    val averageCaloriesPerDay: Int,
    val averageMealPerDay: Double,
    val caloriesPerDay: List<Int>,
    val nutrientsPercentage: NutrientsPercentage) {

    data class NutrientsPercentage(
        val fat: Int,
        val carbs: Int,
        val protein: Int)
}
