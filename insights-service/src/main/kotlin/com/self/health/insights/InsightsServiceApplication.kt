package com.self.health.insights

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.scheduling.annotation.EnableAsync

@EnableKafka
@EnableAsync
@SpringBootApplication
class InsightsServiceApplication

fun main(args: Array<String>) {
	runApplication<InsightsServiceApplication>(*args)
}
