package com.self.health.insights.infrastructure.event.inbound

import com.self.health.insights.application.service.InsightsApplicationService
import com.self.health.meal.domain.event.MealEvent
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.logging.log4j.LogManager
import org.springframework.boot.CommandLineRunner
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux


@Component
class MealListener(private val insightsApplicationService: InsightsApplicationService, private val reactiveKafkaConsumerTemplate: ReactiveKafkaConsumerTemplate<String, MealEvent>):
    CommandLineRunner {
    companion object {
        private val logger = LogManager.getLogger(
            MealListener::class.java
        )
    }

    private fun consumeMealEvent(): Flux<MealEvent> {
        return reactiveKafkaConsumerTemplate
            .receiveAutoAck() // .delayElements(Duration.ofSeconds(2L)) // BACKPRESSURE
            .doOnNext { consumerRecord: ConsumerRecord<String, MealEvent> ->
                    logger.info(
                        "received key={}, value={} from topic={}, offset={}",
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset()
                    )
            }
            .map { obj -> obj.value() }
            .doOnNext { mealEvent: Any? ->
                logger.info(
                    "successfully consumed {}={}",
                    MealEvent::class.java.simpleName, mealEvent
                )
                insightsApplicationService.handle(mealEvent as MealEvent)
            }
            .doOnError { throwable: Throwable ->
                logger.error(
                    "something bad happened while consuming : {}",
                    throwable.message
                )
            }
    }

    override fun run(vararg args: String?) {
        consumeMealEvent().subscribe()
    }
}