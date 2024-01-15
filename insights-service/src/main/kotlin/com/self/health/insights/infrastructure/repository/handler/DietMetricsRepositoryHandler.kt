package com.self.health.insights.infrastructure.repository.handler

import com.self.health.insights.application.repository.DietMetricsRepository
import com.self.health.insights.domain.constant.MetricType
import com.self.health.insights.domain.entity.DietMetrics
import com.self.health.insights.domain.valueobject.Time
import com.self.health.insights.infrastructure.factory.MetricsRepositoryFactory
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import com.self.health.insights.infrastructure.repository.mongo.repository.DietMetricsRepository as InfrastructureRepository

@Repository
class DietMetricsRepositoryHandler(private val dietMetricsRepository: InfrastructureRepository): DietMetricsRepository {
    override fun getDietMetricsByUserIdAndTime(userId: String, time: Time): Mono<DietMetrics> {
        return dietMetricsRepository.getDietMetricsByUserIdAndTime(userId, time.week, time.year, MetricType.WEEKLY.name)
            .flatMap { Mono.just(MetricsRepositoryFactory.domainObjectFrom(it)) }
            .switchIfEmpty(Mono.just(MetricsRepositoryFactory.getDefaultDietMetrics(userId, time)))
    }

    override fun saveDietMetric(dietMetrics: DietMetrics): Mono<Void> {
        val dietMetricDao = MetricsRepositoryFactory.daoObjectFrom(dietMetrics)
        return dietMetricsRepository.save(dietMetricDao).thenEmpty(Mono.empty())
    }

    override fun getMetricsByByUserIdAndTimeBetween(userId: String, time: Time): Flux<DietMetrics> {
        return dietMetricsRepository.getDietMetricsByByUserIdAndTimeBetween(userId, time.week - 7, time.week, time.year, MetricType.WEEKLY.name)
            .map { MetricsRepositoryFactory.domainObjectFrom(it) }
    }
}