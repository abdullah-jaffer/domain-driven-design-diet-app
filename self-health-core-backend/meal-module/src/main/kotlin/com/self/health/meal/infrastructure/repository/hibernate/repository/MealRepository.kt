package com.self.health.meal.infrastructure.repository.hibernate.repository

import com.self.health.meal.infrastructure.repository.dao.MealDAO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MealRepository: JpaRepository<MealDAO, String>