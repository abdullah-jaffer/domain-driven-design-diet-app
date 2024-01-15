package com.self.health.meal.application.service

import com.self.health.meal.application.command.CreateMealCommand
import com.self.health.meal.application.factory.MealApplicationFactory
import com.self.health.meal.application.repository.MealRepository
import com.self.health.meal.application.usecase.CreateMealUseCase
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MealApplicationService(
    private val mealRepository: MealRepository,
    private val foodApplicationFactory: MealApplicationFactory,
    private val applicationEventPublisher: ApplicationEventPublisher
):
    CreateMealUseCase {

    @Transactional("mealTransactionManager")
    override fun handle(createMealCommand: CreateMealCommand) {
        val meal = foodApplicationFactory.mealFrom(createMealCommand)
        mealRepository.saveMeal(meal)
        meal.domainEvents().forEach { applicationEventPublisher.publishEvent(it) }
    }


}