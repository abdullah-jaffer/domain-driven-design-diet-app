package com.self.health.food.infrastructure.config

import liquibase.integration.spring.SpringLiquibase
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import javax.sql.DataSource

@Configuration
class FoodLiquibaseConfiguration {

    @Value("\${liquibase.datasource.food-db.changelog}")
    private val changelog: String = ""

    @Bean
    @ConfigurationProperties(prefix = "liquibase.datasource.food-db")
    fun foodLiquibaseDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    fun foodLiquibaseDataSource(@Qualifier("foodLiquibaseDataSourceProperties") dataSourceProperties: DataSourceProperties): DataSource {
        return dataSourceProperties.initializeDataSourceBuilder().type(SimpleDriverDataSource::class.java).build()
    }

    @Bean
    fun foodLiquibase(@Qualifier("foodLiquibaseDataSource") dataSource: DataSource): SpringLiquibase? {
        val liquibase = SpringLiquibase()
        liquibase.dataSource = dataSource
        liquibase.changeLog = changelog
        return liquibase
    }

}