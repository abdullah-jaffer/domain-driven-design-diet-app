package com.self.health.insights.domain.factory

import com.self.health.insights.domain.constant.MetricType.WEEKLY
import com.self.health.insights.domain.entity.DietMetrics
import com.self.health.insights.domain.valueobject.Time

class DomainFactory {
    companion object {
        fun nullDietMetrics(): DietMetrics {
            return DietMetrics("", Time(0, 0), WEEKLY, "", emptyList())
        }
    }
}