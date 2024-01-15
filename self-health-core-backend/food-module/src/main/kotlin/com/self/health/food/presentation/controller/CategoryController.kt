package com.self.health.food.presentation.controller

import com.self.health.food.application.service.CategoryApplicationService
import com.self.health.food.presentation.controller.dto.response.CategoryResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/category")
internal class CategoryController(private val categoryApplicationService: CategoryApplicationService) {

    @GetMapping
    fun getCategories(): List<CategoryResponseDto> {
        val categories =  categoryApplicationService.handle()
        return categories.map {
            CategoryResponseDto(
                it.id,
                it.name
            )
        }
    }
}