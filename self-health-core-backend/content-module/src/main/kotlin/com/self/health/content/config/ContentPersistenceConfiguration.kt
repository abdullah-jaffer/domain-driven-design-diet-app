package com.self.health.content.config

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
@EntityScan("com.self.health.content")
@EnableJpaRepositories(
    entityManagerFactoryRef = "contentEntityManagerFactory",
    transactionManagerRef = "contentTransactionManager",
    basePackages = ["com.self.health.content"])
@EnableTransactionManagement
class ContentPersistenceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "datasource.content-db")
    fun contentDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    fun contentDataSource(@Qualifier("contentDataSourceProperties") dataSourceProperties: DataSourceProperties): DataSource {
        return dataSourceProperties.initializeDataSourceBuilder().build()
    }

    @Bean
    fun contentEntityManagerFactoryBuilder(): EntityManagerFactoryBuilder? {
        return EntityManagerFactoryBuilder(HibernateJpaVendorAdapter(), HashMap<String, Any?>(), null)
    }

    @Bean
    fun contentEntityManagerFactory(
        @Qualifier("contentDataSource") hubDataSource: DataSource,
        @Qualifier("contentEntityManagerFactoryBuilder") builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(hubDataSource)
            .packages("com.self.health.content.model")
            .persistenceUnit("com/self/health/content")
            .build()
    }

    @Bean
    fun contentTransactionManager(@Qualifier("contentEntityManagerFactory") factory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(factory)
    }
}