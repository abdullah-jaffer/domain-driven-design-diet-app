package com.self.health.insights.application.repository

import com.self.health.insights.domain.entity.DietMetrics
import com.self.health.insights.domain.valueobject.Time
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface DietMetricsRepository {
    fun getDietMetricsByUserIdAndTime(userId: String, time: Time): Mono<DietMetrics>
    fun saveDietMetric(dietMetrics: DietMetrics): Mono<Void>
    fun getMetricsByByUserIdAndTimeBetween(userId: String, time: Time): Flux<DietMetrics>
}