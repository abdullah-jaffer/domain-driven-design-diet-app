package com.self.health.food.presentation.controller.advice

import com.self.health.food.domain.exception.FoodItemToDeleteNotFoundException
import com.self.health.food.domain.exception.ReachedFoodLimitException
import org.apache.commons.lang3.StringUtils
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice(basePackages = ["com.self.health"])
class FoodModuleExceptionsAdvice {

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

    @ExceptionHandler(FoodItemToDeleteNotFoundException::class)
    fun handleFoodItemToDeleteNotFoundException(ex: FoodItemToDeleteNotFoundException): ProblemDetail {
        logger.error(
            MESSAGE_TEMPLATE,
            ex.javaClass.name, StringUtils.join(listOf(ex.message), ","), ex
        )

        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.message!!)
        problemDetail.title = "Cannot find the item to delete"
        problemDetail.setProperty("type", "business exception")
        return problemDetail
    }

    @ExceptionHandler(ReachedFoodLimitException::class)
    fun handleReachedFoodLimitException(ex: ReachedFoodLimitException): ProblemDetail {
        logger.error(
            MESSAGE_TEMPLATE,
            ex.javaClass.name, StringUtils.join(listOf(ex.message), ","), ex
        )

        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.message!!)
        problemDetail.title = "You cannot save more then 10 custom meals at a time, please remove some to add new ones"
        problemDetail.setProperty("type", "business exception")
        return problemDetail
    }

    companion object {
        private const val MESSAGE_TEMPLATE = "An {} exception has occurred, errors : [{}]"
        private val logger = LogManager.getLogger(
            FoodModuleExceptionsAdvice::class.java
        )
    }
}