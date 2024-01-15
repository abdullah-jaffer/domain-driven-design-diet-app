package com.self.health.insights.presentation.controller

import com.self.health.insights.application.command.FetchDashboardCommand
import com.self.health.insights.application.service.InsightsApplicationService
import com.self.health.insights.domain.valueobject.MetricsDashboard
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/v1/insights")
internal class InsightsController(private val insightsApplicationService: InsightsApplicationService) {

    @GetMapping
    fun fetchWeeklyInsights(@RequestHeader("userId") userId: String, @RequestParam localizedTimeStamp: String): Flux<MetricsDashboard> {
        return insightsApplicationService.handle(FetchDashboardCommand(userId, localizedTimeStamp))
    }
}