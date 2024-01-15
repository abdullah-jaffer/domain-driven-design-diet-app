package com.self.health.food.infrastructure.repository.dao

import com.self.health.food.domain.enum.FoodType
import com.self.health.food.domain.enum.ItemStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.Table


@Entity
@Table(name = "food_item")
class FoodItemDAO(
    id:String? = null,
    @Column(name = "description")
    val description: String,
    @Column(name = "nutrition_bank_number")
    val nutritionBankNumber: String,
    @Column(name = "carbohydrates")
    val carbohydrates: Double,
    @Column(name = "cholesterol")
    val cholesterol: Long,
    @Column(name = "protein")
    val protein: Double,
    @Column(name = "sugar")
    val sugar: Double,
    @Column(name = "mono_saturated_fat")
    val monoSaturatedFat: Double,
    @Column(name = "poly_saturated_fat")
    val polySaturatedFat: Double,
    @Column(name = "saturated_fat")
    val saturatedFat: Double,
    @Column(name = "type")
    @Enumerated(STRING)
    val type: FoodType,
    @Column(name = "quantity")
    val quantity: Long,
    @Column(name = "unit")
    val unit: String,
    @Column(name = "image")
    val image: String,
    @Column(name = "category_id")
    val categoryId: String,
    @Column(name = "status")
    @Enumerated(STRING)
    val status: ItemStatus
): BaseDAO(id)