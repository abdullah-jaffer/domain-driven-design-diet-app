package com.self.health.meal.presentation.controller

import com.self.health.meal.application.service.MealApplicationService
import com.self.health.meal.presentation.controller.dto.request.MealCreateDto
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/meal")
internal class MealController(private val mealApplicationService: MealApplicationService) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun createUserFood(@RequestHeader("userId") userId: String, @RequestBody mealCreateDto: MealCreateDto) {
        mealApplicationService.handle(mealCreateDto.toCreateMealCommand(userId))
    }
}