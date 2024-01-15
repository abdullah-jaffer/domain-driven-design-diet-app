package com.self.health.user.advice

import com.self.health.user.SelfHealthCoreBackendApplication.Companion.BASE_PACKAGES
import com.self.health.user.exception.expected.EmailAlreadyInUseException
import org.apache.commons.lang3.StringUtils
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackages = ["$BASE_PACKAGES.user.controller"])
class UserExceptionAdvice {

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<Any> {
        logger.error(
            MESSAGE_TEMPLATE,
            ex.javaClass.name, StringUtils.join(listOf(ex.message), ","), ex
        )
        return ResponseEntity(
            mapOf("errors" to listOf(DEFAULT_ERROR_MESSAGE)),
            INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(EmailAlreadyInUseException::class)
    fun handleEmailAlreadyInUseException(ex: EmailAlreadyInUseException): ResponseEntity<Any> {
        val errors = listOf(ex.message)
        logger.error(
            MESSAGE_TEMPLATE,
            ex.javaClass.name,
            StringUtils.join(errors, ","),
            ex
        )
        return ResponseEntity(
            mapOf("errors" to errors), BAD_REQUEST
        )
    }

    companion object {
        private const val DEFAULT_ERROR_MESSAGE = "Unknown exception occurred, please try again"
        private const val MESSAGE_TEMPLATE = "An {} exception has occurred, errors : [{}]"
        private val logger = LogManager.getLogger(
            UserExceptionAdvice::class.java
        )
    }
}