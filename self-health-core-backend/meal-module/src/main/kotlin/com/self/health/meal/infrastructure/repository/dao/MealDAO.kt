package com.self.health.meal.infrastructure.repository.dao

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table


@Entity
@Table(name = "meal")
class MealDAO(
    id:String? = null,
    @Column(name = "total_calories")
    val totalCalories: Double,
    @Column(name = "user_id")
    val userId: String,
    @Column(name = "localized_time_string")
    val localizedTime: String,
): BaseDAO(id)