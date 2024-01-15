package com.self.health.user.config

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import java.util.Map

internal class UserLiquibaseConfigurationTest {
    lateinit var liquibaseConfiguration: UserLiquibaseConfiguration

    @BeforeEach
    fun beforeEach() {
        liquibaseConfiguration = UserLiquibaseConfiguration()
    }

    @Nested
    internal inner class DataSourceProperties {
        @Test
        fun DataSourcePropertiesTest() {
            assertNotNull(liquibaseConfiguration.userLiquibaseDataSourceProperties())
        }
    }

    @Nested
    internal inner class DataSource {
        @Test
        fun DataSourceTest() {
            val dataSourceProperties = org.springframework.boot.autoconfigure.jdbc.DataSourceProperties()
            dataSourceProperties.username = "root"
            dataSourceProperties.password = "my-secret-pw"
            dataSourceProperties.url = "jdbc:mysql://localhost:3306/bazaar_user"
            assertNotNull(liquibaseConfiguration.userLiquibaseDataSource(dataSourceProperties))
        }
    }

    @Nested
    internal inner class userLiquibase {
        @Test
        fun liquibase() {
            val factory = EntityManagerFactoryBuilder(
                HibernateJpaVendorAdapter(), Map.of<String, Any?>(), null
            )
            val dataSourceProperties = org.springframework.boot.autoconfigure.jdbc.DataSourceProperties()
            dataSourceProperties.username = "root"
            dataSourceProperties.password = "my-secret-pw"
            dataSourceProperties.url = "jdbc:mysql://localhost:3306/bazaar_user"
            val dataSource: javax.sql.DataSource = liquibaseConfiguration.userLiquibaseDataSource(dataSourceProperties)
            val liquibase = liquibaseConfiguration.userLiquibase(dataSource)
            assertNotNull(liquibase)
            assertEquals("", liquibase?.changeLog)
            assertEquals(dataSource, liquibase?.dataSource)
        }
    }

}