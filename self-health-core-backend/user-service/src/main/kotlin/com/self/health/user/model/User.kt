package com.self.health.user.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table


@Entity
@Table(name = "user")
class User(
    id:String? = null,
    @Column(name = "name")
    val name: String,
    @Column(name = "email")
    val email: String,
    @Column(name = "date_of_birth", columnDefinition = "DATE")
    val dateOfBirth: String,
    @Column(name = "external_id")
    val externalId: String,
): BaseModel(id)