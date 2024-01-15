package com.self.health.user.config

import jakarta.persistence.EntityManagerFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import java.util.Map

internal class UserPersistenceConfigurationTest {
    lateinit var userPersistenceConfiguration: UserPersistenceConfiguration

    @BeforeEach
    fun beforeEach() {
        userPersistenceConfiguration = UserPersistenceConfiguration()
    }

    @Nested
    internal inner class DataSourceProperties {
        @Test
        fun DataSourcePropertiesTest() {
            Assertions.assertNotNull(userPersistenceConfiguration.userDataSourceProperties())
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
            Assertions.assertNotNull(userPersistenceConfiguration.userDataSource(dataSourceProperties))
        }
    }

    @Nested
    internal inner class userEntityManagerFactory {
        @Test
        fun userEntityManagerFactoryTest() {
            val factory = EntityManagerFactoryBuilder(
                HibernateJpaVendorAdapter(), Map.of<String, Any?>(), null)
            val dataSourceProperties = org.springframework.boot.autoconfigure.jdbc.DataSourceProperties()
            dataSourceProperties.username = "root"
            dataSourceProperties.password = "my-secret-pw"
            dataSourceProperties.url = "jdbc:mysql://localhost:3306/bazaar_user"
            val dataSource: javax.sql.DataSource = userPersistenceConfiguration.userDataSource(dataSourceProperties)
            Assertions.assertNotNull(userPersistenceConfiguration.userEntityManagerFactory(dataSource, factory))
        }
    }

    @Nested
    internal inner class PlatformTransactionManager {
        @Test
        fun PlatformTransactionManagerTest() {
            val managerMock = Mockito.mock(EntityManagerFactory::class.java)
            Assertions.assertNotNull(userPersistenceConfiguration.userTransactionManager(managerMock))
        }
    }
}