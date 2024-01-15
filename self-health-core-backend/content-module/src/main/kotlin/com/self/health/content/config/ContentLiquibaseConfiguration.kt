package com.self.health.content.config

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
class ContentLiquibaseConfiguration {

    @Value("\${liquibase.datasource.content-db.changelog}")
    private val changelog: String = ""

    @Bean
    @ConfigurationProperties(prefix = "liquibase.datasource.content-db")
    fun contentLiquibaseDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    fun contentLiquibaseDataSource(@Qualifier("contentLiquibaseDataSourceProperties") dataSourceProperties: DataSourceProperties): DataSource {
        return dataSourceProperties.initializeDataSourceBuilder().type(SimpleDriverDataSource::class.java).build()
    }

    @Bean
    fun contentLiquibase(@Qualifier("contentLiquibaseDataSource") dataSource: DataSource): SpringLiquibase? {
        val liquibase = SpringLiquibase()
        liquibase.dataSource = dataSource
        liquibase.changeLog = changelog
        return liquibase
    }

}