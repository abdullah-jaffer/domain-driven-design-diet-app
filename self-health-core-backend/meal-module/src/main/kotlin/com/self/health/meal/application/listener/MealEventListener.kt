package com.self.health.meal.application.listener

import com.self.health.meal.domain.event.MealEvent
import com.self.health.meal.infrastructure.event.outbound.MealProducerKafka
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class MealEventListener(private val mealProducerKafka: MealProducerKafka) {

    @Async
    @EventListener
    fun handleMealCreatedEventListener(mealCreatedEvent: MealEvent) {
        mealProducerKafka.mealCreated(mealCreatedEvent.payload.id, mealCreatedEvent)
    }

}