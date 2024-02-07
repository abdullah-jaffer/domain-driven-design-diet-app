package com.self.health.content.controller

import com.self.health.content.controller.dto.response.PostResponseDto
import com.self.health.content.service.ContentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/content")
class ContentController(private val contentService: ContentService) {

    @GetMapping
    fun fetchPosts(): List<PostResponseDto>  {
        return contentService.getPosts()
    }

}