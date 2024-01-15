package com.self.health.food.infrastructure.repository.repository.handler

import com.self.health.food.application.query.FoodGeneralReadModel
import com.self.health.food.domain.entity.FoodItem
import com.self.health.food.application.repository.FoodRepository
import com.self.health.food.domain.aggregate.UserFoodCatalog
import com.self.health.food.infrastructure.factory.FoodObjectFactory
import com.self.health.food.infrastructure.repository.dao.FoodItemDAO
import com.self.health.food.infrastructure.repository.hibernate.repository.FoodItemRepository
import com.self.health.food.infrastructure.repository.hibernate.repository.FoodItemUserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class FoodRepositoryHandler(
    private val foodItemRepository: FoodItemRepository,
    private val foodItemUserRepository: FoodItemUserRepository,
    private val foodObjectFactory: FoodObjectFactory): FoodRepository {

    override fun getProductsBySearchKeyAndUser(
        searchKey: String,
        userId: String,
        page: Int,
        size: Int): List<FoodItem> {

        val foodItemsFromDB: List<FoodItemDAO> = foodItemRepository.findFoodItems(
            searchKey,
            userId,
            PageRequest.of(page, size))

        return foodObjectFactory.foodItemsFrom(foodItemsFromDB)
    }

    override fun findFoodItemsByUserId(userId: String): List<FoodGeneralReadModel> {
        return foodItemRepository.findFoodItemsReadModelByUserId(userId, PageRequest.of(0, 15))
    }

    override fun getUserFoodCatalogByUserId(userId: String): UserFoodCatalog {
        val foodItems = foodItemRepository.findFoodItemsByUserId(userId, PageRequest.of(0, 15))
        return foodObjectFactory.userFoodCatalogFrom(userId, foodItems)
    }

    override fun saveUserFoodCatalog(userId: String, userFoodCatalog: UserFoodCatalog) {
        val (foodUserDao, foodItemDaoList)  = foodObjectFactory.daoEntitiesFromUserFoodCatalog(userId, userFoodCatalog)
        foodItemUserRepository.deleteAllByUserId(userId)
        foodItemUserRepository.saveAll(foodUserDao)
        foodItemRepository.saveAll(foodItemDaoList)
    }
}