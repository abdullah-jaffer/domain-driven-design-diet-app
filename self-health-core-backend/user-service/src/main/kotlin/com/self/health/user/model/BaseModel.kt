package com.self.health.user.model

import com.devskiller.friendly_id.FriendlyId
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate

import java.io.Serializable
import java.time.Instant

@MappedSuperclass
class BaseModel(
    id: String?,
): Serializable {

    @Id
    val id: String = id ?: FriendlyId.createFriendlyId()

    @Column(name = "created_at", updatable = false)
    private var createdAt: Instant? = null

    @Column(name = "updated_at")
    private var updatedAt: Instant? = null

    @PrePersist
    fun prePersist() {
        createdAt = Instant.now()
        updatedAt = Instant.now()
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = Instant.now()
    }
}