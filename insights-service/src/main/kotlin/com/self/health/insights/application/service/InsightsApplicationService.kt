package com.self.health.insights.application.service

import com.self.health.insights.application.command.FetchDashboardCommand
import com.self.health.insights.application.factory.ApplicationFactory
import com.self.health.insights.application.repository.DietMetricsRepository
import com.self.health.insights.application.usecase.FetchMetricsDashBoardUseCase
import com.self.health.insights.application.usecase.MealCreatedUseCase
import com.self.health.insights.domain.service.InsightsDomainService
import com.self.health.insights.domain.valueobject.MetricsDashboard
import com.self.health.insights.domain.valueobject.Time
import com.self.health.meal.domain.event.MealEvent
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class InsightsApplicationService(private val domainService: InsightsDomainService, private val dietMetricsRepository: DietMetricsRepository): MealCreatedUseCase, FetchMetricsDashBoardUseCase {
    override fun handle(mealEvent: MealEvent) {
        dietMetricsRepository.getDietMetricsByUserIdAndTime(mealEvent.payload.userId, Time(mealEvent.payload.localizedTime))
            .flatMap {
                val domainEvent = ApplicationFactory.domainMealEventFrom(mealEvent)
                val updatedDietMetrics = domainService.updateMetrics(domainEvent, it)
                dietMetricsRepository.saveDietMetric(updatedDietMetrics)
            }.subscribe()
    }

    override fun handle(fetchDashboardCommand: FetchDashboardCommand): Flux<MetricsDashboard> {
        return dietMetricsRepository.getMetricsByByUserIdAndTimeBetween(fetchDashboardCommand.userId, Time(fetchDashboardCommand.timeStamp))
            .map { it.computeMetricsDashBoard() }
    }
}