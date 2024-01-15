package com.self.health.meal.presentation.controller.advice

import com.self.health.meal.domain.exception.CaloriesDoNotMatchException
import com.self.health.meal.domain.exception.EmptyMealItemsListException
import org.apache.commons.lang3.StringUtils
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice(basePackages = ["com.self.health"])
class MealModuleExceptionsAdvice {

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ProblemDetail {
        logger.error(
            MESSAGE_TEMPLATE,
            ex.javaClass.name, StringUtils.join(listOf(ex.message), ","), ex
        )

        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.message!!)
        problemDetail.title = "Some Problem occurred, please try again later"
        problemDetail.setProperty("type", "unknown exception")
        return problemDetail
    }

    @ExceptionHandler(CaloriesDoNotMatchException::class)
    fun handleCaloriesDoNotMatchException(ex: CaloriesDoNotMatchException): ProblemDetail {
        logger.error(
            MESSAGE_TEMPLATE,
            ex.javaClass.name, StringUtils.join(listOf(ex.message), ","), ex
        )

        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.message!!)
        problemDetail.title = "Total calories do not match"
        problemDetail.setProperty("type", "business exception")
        return problemDetail
    }

    @ExceptionHandler(EmptyMealItemsListException::class)
    fun handleEmptyMealItemsListException(ex: EmptyMealItemsListException): ProblemDetail {
        logger.error(
            MESSAGE_TEMPLATE,
            ex.javaClass.name, StringUtils.join(listOf(ex.message), ","), ex
        )

        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.message!!)
        problemDetail.title = "No food items chosen for meal"
        problemDetail.setProperty("type", "business exception")
        return problemDetail
    }

    companion object {
        private const val MESSAGE_TEMPLATE = "An {} exception has occurred, errors : [{}]"
        private val logger = LogManager.getLogger(
            MealModuleExceptionsAdvice::class.java
        )
    }
}