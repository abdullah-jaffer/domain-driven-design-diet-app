package com.self.health.insights.domain.entity

import com.self.health.insights.domain.constant.MetricType
import com.self.health.insights.domain.valueobject.CalorieIntake
import com.self.health.insights.domain.valueobject.DailyDietMetrics
import com.self.health.insights.domain.valueobject.MetricsDashboard
import com.self.health.insights.domain.valueobject.MetricsDashboard.NutrientsPercentage
import com.self.health.insights.domain.valueobject.Nutrient
import com.self.health.insights.domain.valueobject.Time

data class DietMetrics(
    val id: String?,
    val time: Time,
    val type: MetricType,
    val userId: String,
    var calorieIntake: List<CalorieIntake>) {

    fun isEmptyObject(): Boolean { return this.calorieIntake.isEmpty() }

    fun addCalorieIntakeMetric(calorieIntake: CalorieIntake) {
        this.calorieIntake = (this.calorieIntake + calorieIntake).toList()
    }

    fun computeMetricsDashBoard(): MetricsDashboard {
        val dayMap = mutableMapOf<String, DailyDietMetrics>()

        calorieIntake.forEach {
            val date = Time.getDateFromDateTime(it.timeStamp)
            if (dayMap.containsKey(date)){
                dayMap[date]!!.calories = dayMap[date]!!.calories.plus(it.energy.calories.toInt())
                dayMap[date]!!.meals = dayMap[date]!!.meals.plus(1)
                dayMap[date]!!.nutrients.fat = dayMap[date]!!.nutrients.fat.plus(it.nutrients.fat)
                dayMap[date]!!.nutrients.carbohydrates = dayMap[date]!!.nutrients.carbohydrates.plus(it.nutrients.carbohydrates)
                dayMap[date]!!.nutrients.protein = dayMap[date]!!.nutrients.protein.plus(it.nutrients.protein)

            } else {
                dayMap[date] = DailyDietMetrics(
                    it.energy.calories.toInt(),
                    1,
                    Nutrient(
                        it.nutrients.fat,
                        it.nutrients.carbohydrates,
                        it.nutrients.protein
                    )
                )

            }
        }
        return MetricsDashboard(
            averageCaloriesPerDay(dayMap),
            averageMealsPerDay(dayMap),
            caloriesPerDay(dayMap),
            nutrientPercentage(dayMap)
        )
    }

    private fun averageCaloriesPerDay(dayMap: Map<String, DailyDietMetrics>): Int {
        val totalCaloriesPerDay = dayMap.values.sumOf { it.calories }
        return (totalCaloriesPerDay / dayMap.size)
    }

    private fun averageMealsPerDay(dayMap: Map<String, DailyDietMetrics>): Double {
        val totalMealsPerDay = dayMap.values.sumOf { it.meals }
        return (totalMealsPerDay.toDouble() / dayMap.size.toDouble())
    }

    private fun caloriesPerDay(dayMap: Map<String, DailyDietMetrics>): List<Int> {
        return dayMap.keys.sortedDescending().map { dayMap[it]!!.calories }
    }

    private fun nutrientPercentage(dayMap: Map<String, DailyDietMetrics>): NutrientsPercentage {
        val totalNutrientsSum = dayMap.values.sumOf {
            it.nutrients.fat + it.nutrients.carbohydrates + it.nutrients.protein }.toInt()

        return NutrientsPercentage(
            fatPercentage(dayMap, totalNutrientsSum),
            carbsPercentage(dayMap, totalNutrientsSum),
            proteinPercentage(dayMap, totalNutrientsSum),
        )
    }

    private fun fatPercentage(dayMap: Map<String, DailyDietMetrics>, totalNutrientsSum: Int): Int {
        val fatSum = dayMap.values.sumOf { it.nutrients.fat }
        return ((fatSum/ totalNutrientsSum) * 100).toInt()
    }

    private fun carbsPercentage(dayMap: Map<String, DailyDietMetrics>, totalNutrientsSum: Int): Int {
        val carbsSum = dayMap.values.sumOf { it.nutrients.carbohydrates }
        return ((carbsSum/ totalNutrientsSum) * 100).toInt()
    }

    private fun proteinPercentage(dayMap: Map<String, DailyDietMetrics>, totalNutrientsSum: Int): Int {
        val proteinSum = dayMap.values.sumOf { it.nutrients.protein }
        return ((proteinSum/ totalNutrientsSum) * 100).toInt()
    }
}
