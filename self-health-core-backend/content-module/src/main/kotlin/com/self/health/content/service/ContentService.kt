package com.self.health.content.service

import com.self.health.content.controller.dto.response.PostResponseDto
import com.self.health.content.dto.ContentStatus
import com.self.health.content.repository.ContentRepository
import org.springframework.stereotype.Service

@Service
class ContentService(private val contentRepository: ContentRepository) {

    fun getPosts(): List<PostResponseDto> {
        return contentRepository.findByStatus(ContentStatus.ACTIVE).map {
            PostResponseDto(
                it.id,
                it.title,
                it.image,
                it.link,
                it.status)
        }
    }
}