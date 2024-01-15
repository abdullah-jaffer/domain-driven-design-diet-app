package com.self.health.food.infrastructure.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EntityScan("com.self.health.food")
@EnableJpaRepositories(
    entityManagerFactoryRef = "foodEntityManagerFactory",
    transactionManagerRef = "foodTransactionManager",
    basePackages = ["com.self.health.food"])
@EnableTransactionManagement
class FoodPersistenceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "datasource.food-db")
    fun foodDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    fun foodDataSource(@Qualifier("foodDataSourceProperties") dataSourceProperties: DataSourceProperties): DataSource {
        return dataSourceProperties.initializeDataSourceBuilder().build()
    }

    @Bean
    fun foodEntityManagerFactoryBuilder(): EntityManagerFactoryBuilder? {
        return EntityManagerFactoryBuilder(HibernateJpaVendorAdapter(), HashMap<String, Any?>(), null)
    }

    @Bean
    fun foodEntityManagerFactory(
        @Qualifier("foodDataSource") hubDataSource: DataSource,
        @Qualifier("foodEntityManagerFactoryBuilder") builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(hubDataSource)
            .packages("com.self.health.food.infrastructure.repository.dao")
            .persistenceUnit("food")
            .build()
    }

    @Bean
    fun foodTransactionManager(@Qualifier("foodEntityManagerFactory") factory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(factory)
    }
}