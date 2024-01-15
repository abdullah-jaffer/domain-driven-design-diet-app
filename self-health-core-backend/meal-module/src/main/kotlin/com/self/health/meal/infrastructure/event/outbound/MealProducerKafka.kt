package com.self.health.meal.infrastructure.event.outbound

import com.self.health.meal.domain.event.MealEvent
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class MealProducerKafka(val kafkaTemplate: KafkaTemplate<String, Any>,
                        @Value("\${kafka.topic.meal}") val mealTopic: String) {

    companion object {
        private val logger = LogManager.getLogger(
            MealProducerKafka::class.java
        )
    }

    @Async
    fun mealCreated(mealId: String, value: MealEvent) {
        sendMessage(mealId, value)
    }

    private fun sendMessage(mealId: String, value: MealEvent) {
        val future: CompletableFuture<*> = kafkaTemplate.send(mealTopic, mealId, value)
        future.whenComplete { sendResult: Any?, exception: Throwable? ->
            if (exception != null) {
                logger.error("An exception occurred while sending kafka message", exception)
                future.completeExceptionally(exception)
            } else {
                logger.info("kafka message sent successfully", sendResult)
                future.complete(sendResult as Nothing?)
            }
        }
    }
}