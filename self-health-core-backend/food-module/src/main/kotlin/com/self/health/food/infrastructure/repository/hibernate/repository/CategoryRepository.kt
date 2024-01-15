package com.self.health.food.infrastructure.repository.hibernate.repository

import com.self.health.food.infrastructure.repository.dao.CategoryDAO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: JpaRepository<CategoryDAO, String>