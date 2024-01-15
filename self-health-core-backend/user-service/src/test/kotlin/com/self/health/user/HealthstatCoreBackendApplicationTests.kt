package com.self.health.user

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.util.TestSocketUtils

@SpringBootTest
class HealthStatCoreBackendApplicationTests {

	companion object {
		init {
			System.setProperty("spring.profiles.active", "test")
			System.setProperty("server.port", TestSocketUtils.findAvailableTcpPort().toString())
		}
	}

	@Test
	fun contextLoads() {
		SelfHealthCoreBackendApplication.main()
	}

}
