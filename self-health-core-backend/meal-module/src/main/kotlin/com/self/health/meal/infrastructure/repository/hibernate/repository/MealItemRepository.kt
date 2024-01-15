package com.self.health.meal.infrastructure.repository.hibernate.repository

import com.self.health.meal.infrastructure.repository.dao.MealItemDAO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MealItemRepository: JpaRepository<MealItemDAO, String>