package com.self.health.insights.infrastructure.repository.mongo.repository

import com.self.health.insights.domain.constant.MetricType
import com.self.health.insights.infrastructure.repository.dao.DietMetrics
import org.springframework.data.mongodb.repository.Query

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface DietMetricsRepository : ReactiveMongoRepository<DietMetrics, String> {
    @Query(value = "{ 'userId' : ?0, 'time.week' : ?1, 'time.year': ?2, 'type': ?3 }")
    fun getDietMetricsByUserIdAndTime(userId: String, week: Int, year: Int, type: String): Mono<DietMetrics>

    @Query(value = "{ 'userId' : ?0, 'time.week' : { \$gte: ?1, \$lte: ?2 }, 'time.year': ?3, 'type': ?4 }")
    fun getDietMetricsByByUserIdAndTimeBetween(userId: String, startingWeek: Int, endingWeek: Int, year: Int, type: String): Flux<DietMetrics>
}