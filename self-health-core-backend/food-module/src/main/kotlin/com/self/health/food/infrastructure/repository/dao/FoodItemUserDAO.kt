package com.self.health.food.infrastructure.repository.dao

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table


@Entity
@Table(name = "food_user")
class FoodItemUserDAO(
    id:String? = null,
    @Column(name = "user_id")
    val userId: String,
    @Column(name = "food_item_id")
    val foodItemId: String
): BaseDAO(id)