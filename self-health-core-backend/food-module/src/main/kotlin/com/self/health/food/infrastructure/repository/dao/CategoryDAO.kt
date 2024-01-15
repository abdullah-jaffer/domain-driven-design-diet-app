package com.self.health.food.infrastructure.repository.dao

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table


@Entity
@Table(name = "category")
class CategoryDAO(
    id:String? = null,
    @Column(name = "name")
    val name: String
): BaseDAO(id)