package com.self.health.meal.domain.exception

class CaloriesDoNotMatchException(userId: String): RuntimeException("calories do not match for userId: $userId") {
}