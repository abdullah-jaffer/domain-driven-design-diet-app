package com.self.health.insights.domain.strategy

import com.self.health.insights.domain.entity.DietMetrics
import com.self.health.insights.domain.event.MealEvent

interface MetricsStrategy {
    fun execute(mealEvent: MealEvent, userDietMetrics: DietMetrics): DietMetrics
}