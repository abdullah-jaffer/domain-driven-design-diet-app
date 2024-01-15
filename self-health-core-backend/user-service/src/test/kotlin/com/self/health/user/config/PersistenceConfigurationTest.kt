//package com.self.health.user.config
//
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
//
//internal class PersistenceConfigurationTest {
//
//    private val url         = "jdbc:mysql://127.0.0.1:15432/user"
//    private val username    = "user"
//    private val password    = "admin"
//
//    lateinit var persistenceConfiguration: PersistenceConfiguration
//
//    @BeforeEach
//    fun beforeEach() {
//        persistenceConfiguration = PersistenceConfiguration()
//    }
//
//    @Test
//    fun `DataSourceProperties Test`() {
//        assertNotNull(persistenceConfiguration.dataSourceProperties())
//    }
//
//    @Test
//    fun `DataSource Test`() {
//        val dataSourceProperties = DataSourceProperties()
//        dataSourceProperties.url = url
//        dataSourceProperties.username = username
//        dataSourceProperties.password = password
//
//        assertNotNull(persistenceConfiguration.dataSource(dataSourceProperties))
//    }
//}