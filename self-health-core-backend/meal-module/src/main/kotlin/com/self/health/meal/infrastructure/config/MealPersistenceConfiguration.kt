package com.self.health.meal.infrastructure.config

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
@EntityScan("com.self.health.meal")
@EnableJpaRepositories(
    entityManagerFactoryRef = "mealEntityManagerFactory",
    transactionManagerRef = "mealTransactionManager",
    basePackages = ["com.self.health.meal"])
@EnableTransactionManagement
class MealPersistenceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "datasource.meal-db")
    fun mealDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    fun mealDataSource(@Qualifier("mealDataSourceProperties") dataSourceProperties: DataSourceProperties): DataSource {
        return dataSourceProperties.initializeDataSourceBuilder().build()
    }

    @Bean
    fun mealEntityManagerFactoryBuilder(): EntityManagerFactoryBuilder? {
        return EntityManagerFactoryBuilder(HibernateJpaVendorAdapter(), HashMap<String, Any?>(), null)
    }

    @Bean
    fun mealEntityManagerFactory(
        @Qualifier("mealDataSource") hubDataSource: DataSource,
        @Qualifier("mealEntityManagerFactoryBuilder") builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(hubDataSource)
            .packages("com.self.health.meal.infrastructure.repository.dao")
            .persistenceUnit("meal")
            .build()
    }

    @Bean
    fun mealTransactionManager(@Qualifier("mealEntityManagerFactory") factory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(factory)
    }
}