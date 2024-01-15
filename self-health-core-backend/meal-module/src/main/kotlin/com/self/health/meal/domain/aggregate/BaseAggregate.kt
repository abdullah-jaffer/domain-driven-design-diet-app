package com.self.health.meal.domain.aggregate

import com.self.health.meal.domain.event.DomainEvent

open class BaseAggregate(private val domainEvents: MutableList<DomainEvent> = mutableListOf()) {
    fun registerEvent(domainEvent: DomainEvent) {
        domainEvents.add(domainEvent)
    }

    fun domainEvents(): List<DomainEvent> {
        return  domainEvents
    }
}