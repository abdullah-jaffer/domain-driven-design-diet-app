//package com.self.health.user.security.config
//
//
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Nested
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.mockito.ArgumentMatchers
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.junit.jupiter.MockitoExtension
//import org.springframework.security.config.annotation.SecurityBuilder
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer
//import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
//import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
//import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
//import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
//import org.springframework.security.web.authentication.HttpStatusEntryPoint
//
//
//@ExtendWith(MockitoExtension::class)
//internal class ApplicationSecurityConfigurationTest {
//    private lateinit var securityConfig: SecurityConfiguration
//
//
//    @BeforeEach
//    fun beforeEach() {
//        securityConfig = SecurityConfiguration()
//    }
//
//    @Nested
//    internal inner class SpringSecurityConfiguration {
//        @Mock
//        private lateinit var httpSecurity: HttpSecurity
//
//        @Mock
//        private lateinit var corsConfigure: CorsConfigurer<HttpSecurity>
//
//        @Mock
//        private lateinit var csrfConfigure: CsrfConfigurer<HttpSecurity>
//
//        @Mock
//        private lateinit var authorizedUrl: AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizedUrl
//
//        @Mock
//        private lateinit var authorizationManagerRequestMatcherRegistry: AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry
//
//        @Mock
//        private lateinit var sessionManagementConfigure: SessionManagementConfigurer<HttpSecurity>
//
//        @Mock
//        private lateinit var exceptionHandlingConfigure: ExceptionHandlingConfigurer<HttpSecurity>
//
//        @Test
//        @Throws(Exception::class)
//        fun configureHttpSecurity() {
//            // CORS should be configured
//            Mockito.`when`(httpSecurity.cors()).thenReturn(corsConfigure)
//            Mockito.`when`<SecurityBuilder<*>?>(corsConfigure.and()).thenReturn(httpSecurity)
//
//            // CSRF should be configured to be disabled
//            Mockito.`when`(httpSecurity.csrf()).thenReturn(csrfConfigure)
//            Mockito.`when`(csrfConfigure.disable()).thenReturn(httpSecurity)
//
//            // All requests should be permitted as authorization would happen via SPeL annotations such as @PreAuthorize
//            Mockito.`when`(httpSecurity.authorizeHttpRequests()).thenReturn(authorizationManagerRequestMatcherRegistry)
//            Mockito.`when`(authorizationManagerRequestMatcherRegistry.anyRequest())
//                .thenReturn(authorizedUrl)
//            Mockito.`when`(authorizedUrl.permitAll())
//                .thenReturn(authorizationManagerRequestMatcherRegistry)
//            Mockito.`when`(authorizationManagerRequestMatcherRegistry.and()).thenReturn(httpSecurity)
//
//            // SessionManagement should be configured with STATELESS policy
//            Mockito.`when`(httpSecurity.sessionManagement()).thenReturn(sessionManagementConfigure)
//            Mockito.`when`(sessionManagementConfigure.sessionCreationPolicy(STATELESS))
//                .thenReturn(sessionManagementConfigure)
//            Mockito.`when`<SecurityBuilder<*>?>(sessionManagementConfigure.and()).thenReturn(httpSecurity)
//
//            // ExceptionHandling should be configured
//            Mockito.`when`(httpSecurity.exceptionHandling()).thenReturn(exceptionHandlingConfigure)
//            Mockito.`when`(
//                exceptionHandlingConfigure.authenticationEntryPoint(
//                    ArgumentMatchers.any(
//                        HttpStatusEntryPoint::class.java
//                    )
//                )
//            ).thenReturn(exceptionHandlingConfigure)
//            Mockito.`when`<SecurityBuilder<*>?>(exceptionHandlingConfigure.and()).thenReturn(httpSecurity)
//            securityConfig.filterChain(httpSecurity)
//            Mockito.verify(httpSecurity, Mockito.times(1)).cors()
//            Mockito.verify(httpSecurity, Mockito.times(1)).csrf()
//            Mockito.verify(csrfConfigure, Mockito.times(1)).disable()
//            Mockito.verify(httpSecurity, Mockito.times(1)).authorizeHttpRequests()
//            Mockito.verify(
//                authorizationManagerRequestMatcherRegistry,
//                Mockito.times(1)
//            ).anyRequest()
//            Mockito.verify(authorizedUrl, Mockito.times(1)).permitAll()
//            Mockito.verify(httpSecurity, Mockito.times(1)).sessionManagement()
//            Mockito.verify(sessionManagementConfigure, Mockito.times(1)).sessionCreationPolicy(STATELESS)
//            Mockito.verify(httpSecurity, Mockito.times(1)).exceptionHandling()
//            Mockito.verify(exceptionHandlingConfigure, Mockito.times(1)).authenticationEntryPoint(
//                ArgumentMatchers.any(
//                    HttpStatusEntryPoint::class.java
//                )
//            )
//        }
//    }
//}