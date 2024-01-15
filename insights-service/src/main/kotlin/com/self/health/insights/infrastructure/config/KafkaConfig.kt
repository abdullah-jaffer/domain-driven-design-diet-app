package com.self.health.insights.infrastructure.config

import com.self.health.meal.domain.event.MealEvent
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate
import reactor.kafka.receiver.ReceiverOptions

@Configuration
class KafkaConfig {
    @Bean
    fun kafkaReceiverOptions(
        @Value(value = "\${kafka.topic.meal}") topic: String,
        kafkaProperties: KafkaProperties
    ): ReceiverOptions<String, MealEvent> {
        val basicReceiverOptions: ReceiverOptions<String, MealEvent> =
            ReceiverOptions.create(kafkaProperties.buildConsumerProperties())
        return basicReceiverOptions.subscription(listOf(topic))
    }

    @Bean
    fun reactiveKafkaConsumerTemplate(kafkaReceiverOptions: ReceiverOptions<String, MealEvent>): ReactiveKafkaConsumerTemplate<String, MealEvent> {
        return ReactiveKafkaConsumerTemplate(kafkaReceiverOptions)
    }
}