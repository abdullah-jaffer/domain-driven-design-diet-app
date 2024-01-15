package com.self.health.insights.domain.valueobject

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*

data class Time(val year: Int,
                val week: Int){
    constructor(dateTimeString: String) : this(getYearFromDateTime(dateTimeString), getWeekFromDateTime(dateTimeString))


    companion object {
        private const val datePattern = "yyyy-MM-dd'T'HH-mm-ssXXX"

        private fun getYearFromDateTime(dateTimeString: String): Int {
            val formatter = DateTimeFormatter.ofPattern(datePattern)
            val localDate = LocalDate.parse(dateTimeString, formatter)
            return localDate.year
        }

        private fun getWeekFromDateTime(dateTimeString: String): Int {
            val formatter = DateTimeFormatter.ofPattern(datePattern)
            val localDate = LocalDate.parse(dateTimeString, formatter)

            val weekFields = WeekFields.of(Locale.getDefault())
            return localDate.get(weekFields.weekOfWeekBasedYear())
        }

        fun getDateFromDateTime(dateTimeString: String): String {
            val formatter = DateTimeFormatter.ofPattern(datePattern)
            val localDate = LocalDate.parse(dateTimeString, formatter)

            return localDate.toString()
        }
    }
}
