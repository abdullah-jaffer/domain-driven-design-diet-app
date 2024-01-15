package com.self.health.user.advice

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.self.health.user.exception.expected.EmailAlreadyInUseException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class UserExceptionAdviceTest {
    private lateinit var exceptionsAdvice: UserExceptionAdvice
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun beforeEach() {
        objectMapper = ObjectMapper()
        exceptionsAdvice = UserExceptionAdvice()
    }

    @Nested
    inner class GenericExceptionHandlerTest {
        private val errorMessage = "SQL Exception"

        @Test
        fun handle() {
            val exception = java.lang.Exception(errorMessage)
            val responseEntity: ResponseEntity<Any> = exceptionsAdvice.handleGenericException(exception)
            val body: JsonNode = objectMapper.convertValue(
                responseEntity.body,
                JsonNode::class.java
            )
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.statusCode)
            assertEquals(DEFAULT_ERROR_MESSAGE, body["errors"][0].asText())
        }
    }

    @Nested
    inner class EmailAlreadyInUseExceptionTest {
        private val email = "user@mail.com"

        @Test
        fun handle() {
            val exception = EmailAlreadyInUseException(email)
            val responseEntity: ResponseEntity<Any> = exceptionsAdvice.handleEmailAlreadyInUseException(exception)
            val body: JsonNode = objectMapper.convertValue(
                responseEntity.body,
                JsonNode::class.java
            )
            assertEquals(HttpStatus.BAD_REQUEST, responseEntity.statusCode)
            assertEquals(exception.message, body["errors"][0].asText())
        }

    }

    companion object {
        private const val DEFAULT_ERROR_MESSAGE = "Unknown exception occurred, please try again"
    }

}