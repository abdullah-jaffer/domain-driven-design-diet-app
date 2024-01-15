package com.self.health.insights.domain.factory

import com.self.health.insights.domain.entity.DietMetrics
import com.self.health.insights.domain.strategy.MetricsStrategy
import com.self.health.insights.domain.strategy.algorithm.AddToOngoingWeek
import com.self.health.insights.domain.strategy.algorithm.NewWeekInitiated

class MetricsStrategyFactory {

    companion object {
        fun getMetricsStrategy(dietMetrics: DietMetrics): MetricsStrategy {
            val strategyMap = mapOf(
                true to NewWeekInitiated(),
                false to AddToOngoingWeek()
            )

            return strategyMap[dietMetrics.isEmptyObject()]!!
        }
    }

}