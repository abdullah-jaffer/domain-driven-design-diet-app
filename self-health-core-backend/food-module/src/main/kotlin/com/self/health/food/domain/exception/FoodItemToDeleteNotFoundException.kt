package com.self.health.food.domain.exception

class FoodItemToDeleteNotFoundException(id: String): RuntimeException("item with id: $id not found for deletion") {
}