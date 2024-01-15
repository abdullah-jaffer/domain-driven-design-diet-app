package com.self.health.insights.domain.strategy.algorithm

import com.self.health.insights.domain.constant.MetricType.WEEKLY
import com.self.health.insights.domain.entity.DietMetrics
import com.self.health.insights.domain.event.MealEvent
import com.self.health.insights.domain.strategy.MetricsStrategy
import com.self.health.insights.domain.valueobject.CalorieIntake
import com.self.health.insights.domain.valueobject.Energy
import com.self.health.insights.domain.valueobject.Nutrient
import com.self.health.insights.domain.valueobject.Time

class NewWeekInitiated: MetricsStrategy {
    override fun execute(mealEvent: MealEvent, dietMetrics: DietMetrics): DietMetrics {
        val energy = Energy(mealEvent.payload.totalCalories)
        val nutrients = Nutrient(mealEvent.totalFats(), mealEvent.totalCarbs(), mealEvent.totalProtein())
        val calorieIntake = listOf(CalorieIntake(energy, nutrients, mealEvent.payload.localizedTime))
        return DietMetrics(dietMetrics.id, Time(mealEvent.payload.localizedTime), WEEKLY, mealEvent.payload.userId, calorieIntake)
    }
}