package com.self.health.content.controller.dto.response

import com.self.health.content.dto.ContentStatus

data class PostResponseDto(val id: String, val title: String, val image: String, val link: String, val status: ContentStatus)