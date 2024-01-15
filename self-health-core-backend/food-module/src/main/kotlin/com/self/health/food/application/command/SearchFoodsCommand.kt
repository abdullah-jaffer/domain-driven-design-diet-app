package com.self.health.food.application.command

data class SearchFoodsCommand(
    val searchKey: String,
    val userId: String,
    val page: Int,
    val size: Int)
