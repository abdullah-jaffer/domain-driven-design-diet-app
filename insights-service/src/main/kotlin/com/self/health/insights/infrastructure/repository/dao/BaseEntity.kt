package com.self.health.insights.infrastructure.repository.dao

import com.devskiller.friendly_id.FriendlyId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable
import java.time.LocalDateTime

open class BaseEntity(
    id: String? = null,
    createdAt: LocalDateTime = LocalDateTime.now(),
    updatedAt: LocalDateTime = LocalDateTime.now()
) : Serializable {
    @Id
    @Field(name = "id")
    open var id: String? = id

    @CreatedDate
    @Field(name = "created_at")
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Field(name = "updated_at")
    var updatedAt: LocalDateTime? = null

    init {
        this.id = id ?: FriendlyId.createFriendlyId()
    }
}