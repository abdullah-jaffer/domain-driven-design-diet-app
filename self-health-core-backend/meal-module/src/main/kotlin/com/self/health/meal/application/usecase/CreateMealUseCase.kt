package com.self.health.meal.application.usecase

import com.self.health.meal.application.command.CreateMealCommand

interface CreateMealUseCase {
    fun handle(createMealCommand: CreateMealCommand)

}