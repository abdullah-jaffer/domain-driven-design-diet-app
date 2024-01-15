package com.self.health.insights.application.factory

import com.self.health.insights.domain.constant.MealEventStatus
import com.self.health.meal.domain.event.MealEvent
import com.self.health.insights.domain.event.MealEvent as DomainMealEvent

class ApplicationFactory {
    companion object {
        fun domainMealEventFrom(externalMealEvent: MealEvent): DomainMealEvent {
            val payload = externalMealEvent.payload
            val mealItems = payload.mealItems.map {
                DomainMealEvent.MealItem(it.calories, it.carbohydrates, it.protein, it.fat, it.quantity, it.unit)
            }
            val domainPayload = DomainMealEvent.Payload(payload.id, payload.userId, payload.totalCalories, payload.localizedTime, mealItems)
            return DomainMealEvent(MealEventStatus.valueOf(externalMealEvent.eventType.name), domainPayload)
        }


    }
}