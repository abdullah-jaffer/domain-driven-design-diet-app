package com.self.health.meal.domain.exception

class EmptyMealItemsListException(userId: String): RuntimeException("no meal items entered by user: $userId") {
}