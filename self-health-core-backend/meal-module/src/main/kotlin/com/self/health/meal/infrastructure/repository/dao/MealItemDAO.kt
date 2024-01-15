package com.self.health.meal.infrastructure.repository.dao

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "meal_item")
class MealItemDAO(
    id:String? = null,
    @Column(name = "calories")
    val calories: Double,
    @Column(name = "carbohydrates")
    val carbohydrates: Double,
    @Column(name = "protein")
    val protein: Double,
    @Column(name = "fat")
    val fat: Double,
    @Column(name = "quantity")
    val quantity: Long,
    @Column(name = "unit")
    val unit: String,
    @Column(name = "meal_id")
    val mealId: String
): BaseDAO(id)