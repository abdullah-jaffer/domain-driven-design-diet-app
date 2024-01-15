package com.self.health.insights.domain.service

import com.self.health.insights.domain.entity.DietMetrics
import com.self.health.insights.domain.event.MealEvent
import com.self.health.insights.domain.factory.DomainFactory
import com.self.health.insights.domain.factory.MetricsStrategyFactory
import org.springframework.stereotype.Service

@Service
class InsightsDomainService {

    fun updateMetrics(mealEvent: MealEvent, dietMetrics: DietMetrics = DomainFactory.nullDietMetrics()): DietMetrics {
        val strategy = MetricsStrategyFactory.getMetricsStrategy(dietMetrics)
        return strategy.execute(mealEvent, dietMetrics)
    }
}