package com.self.health.user.config

import com.self.health.user.SelfHealthCoreBackendApplication.Companion.BASE_PACKAGES
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


@EnableJpaRepositories(
    entityManagerFactoryRef = "userEntityManagerFactory",
    transactionManagerRef = "userTransactionManager",
    basePackages = ["$BASE_PACKAGES.user.repository"])@Configuration
@EntityScan(BASE_PACKAGES)

@EnableTransactionManagement
class UserPersistenceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "datasource.user")
    fun userDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    fun userDataSource(@Qualifier("userDataSourceProperties") dataSourceProperties: DataSourceProperties): DataSource {
        return dataSourceProperties.initializeDataSourceBuilder().build()
    }

    @Bean
    fun entityManagerFactoryBuilder(): EntityManagerFactoryBuilder? {
        return EntityManagerFactoryBuilder(HibernateJpaVendorAdapter(), HashMap<String, Any?>(), null)
    }

    @Bean
    fun userEntityManagerFactory(
        @Qualifier("userDataSource") hubDataSource: DataSource,
        @Qualifier("entityManagerFactoryBuilder") builder:  EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(hubDataSource)
            .packages("$BASE_PACKAGES.user")
            .persistenceUnit("user")
            .build()
    }

    @Bean
    fun userTransactionManager(@Qualifier("userEntityManagerFactory") factory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(factory)
    }
}