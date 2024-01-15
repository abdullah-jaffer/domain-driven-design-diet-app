package com.self.health.food.domain.exception

class ReachedFoodLimitException(customMessage: String = "You have reached your custom meal limit"): RuntimeException(customMessage) {
}