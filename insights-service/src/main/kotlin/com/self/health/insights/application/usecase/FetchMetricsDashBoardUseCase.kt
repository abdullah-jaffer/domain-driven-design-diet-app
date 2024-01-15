package com.self.health.insights.application.usecase

import com.self.health.insights.application.command.FetchDashboardCommand
import com.self.health.insights.domain.valueobject.MetricsDashboard
import reactor.core.publisher.Flux

interface FetchMetricsDashBoardUseCase {
    fun handle(fetchDashboardCommand: FetchDashboardCommand): Flux<MetricsDashboard>
}