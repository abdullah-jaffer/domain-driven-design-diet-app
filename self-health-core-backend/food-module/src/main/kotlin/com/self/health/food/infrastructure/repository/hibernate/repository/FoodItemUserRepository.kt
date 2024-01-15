package com.self.health.food.infrastructure.repository.hibernate.repository

import com.self.health.food.infrastructure.repository.dao.FoodItemUserDAO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodItemUserRepository: JpaRepository<FoodItemUserDAO, String> {
    fun findByUserId(userId: String): List<FoodItemUserDAO>
    fun deleteAllByUserId(userId: String)
}