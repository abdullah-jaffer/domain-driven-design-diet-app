package com.self.health.content.repository

import com.self.health.content.dto.ContentStatus
import com.self.health.content.model.Content
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContentRepository: JpaRepository<Content, String> {
    fun findByStatus(status: ContentStatus): List<Content>
}