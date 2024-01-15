package com.self.health.food.presentation.controller

import com.self.health.food.application.command.DeleteFoodItemCommand
import com.self.health.food.application.command.FetchUserFoodsCommand
import com.self.health.food.application.command.SearchFoodsCommand
import com.self.health.food.application.service.FoodApplicationService
import com.self.health.food.presentation.controller.dto.request.FoodItemCreateDto
import com.self.health.food.presentation.controller.dto.response.FoodGeneralResponseDto
import com.self.health.food.presentation.controller.dto.response.FoodSearchResponseDto
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/food")
internal class FoodController(private val foodApplicationService: FoodApplicationService) {

    @GetMapping("/search-key/{searchKey}")
    fun searchFoods(@PathVariable searchKey: String,
                    @RequestHeader("userId") userId: String,
                    @RequestParam(defaultValue = "0") page: Int,
                    @RequestParam(defaultValue = "0") size: Int): List<FoodSearchResponseDto> {
        val foodSearchModel =  foodApplicationService.handle(SearchFoodsCommand(
            searchKey = searchKey,
            userId = userId,
            page = page,
            size = size))

        return foodSearchModel.map {
            FoodSearchResponseDto(
                it.id,
                it.name,
                it.calories,
                it.quantity,
                it.unit,
                it.image
            )
        }
    }

    @GetMapping("/user")
    fun fetchUserFoods(@RequestHeader("userId") userId: String): List<FoodGeneralResponseDto> {
        val foodGeneralModel = foodApplicationService.handle(FetchUserFoodsCommand(userId))
        return foodGeneralModel.map {
            FoodGeneralResponseDto(
                it.id,
                it.name,
                it.quantity,
                it.unit,
                it.image
            )
        }
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun createUserFood(@RequestHeader("userId") userId: String, @RequestBody foodItemCreationDto: FoodItemCreateDto) {
        foodApplicationService.handle(foodItemCreationDto.toFoodItemCreateCommand(userId))
    }

    @DeleteMapping("/{id}")
    fun deleteFoodItem(@RequestHeader("userId") userId: String, @PathVariable id: String) {
        foodApplicationService.handle(DeleteFoodItemCommand(userId, id))
    }
}