package com.self.health.insights.infrastructure.factory

import com.devskiller.friendly_id.FriendlyId
import com.self.health.insights.domain.constant.MetricType.WEEKLY
import com.self.health.insights.domain.entity.DietMetrics
import com.self.health.insights.domain.valueobject.CalorieIntake
import com.self.health.insights.domain.valueobject.Energy
import com.self.health.insights.domain.valueobject.Nutrient
import com.self.health.insights.domain.valueobject.Time
import com.self.health.insights.infrastructure.repository.dao.DietMetrics as DietMetricsDao

class MetricsRepositoryFactory {
    companion object {
        fun getDefaultDietMetrics(userId: String, time: Time): DietMetrics {
            return DietMetrics(FriendlyId.createFriendlyId(), time, WEEKLY, userId, emptyList())
        }

        fun domainObjectFrom(dietMetrics: com.self.health.insights.infrastructure.repository.dao.DietMetrics): DietMetrics {
            return DietMetrics(
                dietMetrics.id,
                Time(dietMetrics.time.year, dietMetrics.time.week),
                dietMetrics.type,
                dietMetrics.userId,
            dietMetrics.calorieIntake.map {
                CalorieIntake(
                    Energy(it.energy.calories),
                    Nutrient(
                        it.nutrients.fat,
                        it.nutrients.carbohydrates,
                        it.nutrients.protein),
                    it.timeStamp)
                }
            )
        }

        fun daoObjectFrom(dietMetrics: DietMetrics): DietMetricsDao {
            return DietMetricsDao(
                dietMetrics.id!!,
                DietMetricsDao.Time(dietMetrics.time.year, dietMetrics.time.week),
                dietMetrics.type,
                dietMetrics.userId,
                dietMetrics.calorieIntake.map {
                    DietMetricsDao.CalorieIntake(
                        DietMetricsDao.Energy(it.energy.calories),
                        DietMetricsDao.Nutrient(
                            it.nutrients.fat,
                            it.nutrients.carbohydrates,
                            it.nutrients.protein),
                        it.timeStamp)
                }
            )
        }
    }
}