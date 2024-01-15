package com.self.health.user

import com.self.health.user.SelfHealthCoreBackendApplication.Companion.BASE_PACKAGES
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@EnableKafka
@SpringBootApplication(scanBasePackages = ["$BASE_PACKAGES"])
class SelfHealthCoreBackendApplication {
companion object {
	const val BASE_PACKAGES = "com.self.health"

	@JvmStatic
	fun main(vararg args: String) {
		runApplication<SelfHealthCoreBackendApplication>(*args)
	}
 }
}




