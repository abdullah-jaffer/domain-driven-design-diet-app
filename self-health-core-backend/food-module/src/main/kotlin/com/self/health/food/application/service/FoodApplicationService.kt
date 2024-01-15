package com.self.health.food.application.service

import com.self.health.food.application.command.CreateFoodItemCommand
import com.self.health.food.application.command.DeleteFoodItemCommand
import com.self.health.food.application.command.FetchUserFoodsCommand
import com.self.health.food.application.command.SearchFoodsCommand
import com.self.health.food.application.query.FoodSearchReadModel
import com.self.health.food.application.usecase.SearchFoodUseCase
import com.self.health.food.application.repository.FoodRepository
import com.self.health.food.application.factory.FoodApplicationFactory
import com.self.health.food.application.query.FoodGeneralReadModel
import com.self.health.food.application.usecase.CreateCustomFoodItemUseCase
import com.self.health.food.application.usecase.ListUserFoodItemsUseCase
import com.self.health.food.application.usecase.RemoveCustomFoodItemUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FoodApplicationService(
    private val foodDomainRepository: FoodRepository,
    private val foodApplicationFactory: FoodApplicationFactory):
    SearchFoodUseCase,
    ListUserFoodItemsUseCase,
    CreateCustomFoodItemUseCase,
    RemoveCustomFoodItemUseCase {

    override fun handle(searchFoodsCommand: SearchFoodsCommand): List<FoodSearchReadModel> {
        val foodItems = foodDomainRepository.getProductsBySearchKeyAndUser(
            searchKey = searchFoodsCommand.searchKey,
            userId = searchFoodsCommand.userId,
            page = searchFoodsCommand.page,
            size = searchFoodsCommand.size)

        return foodApplicationFactory.foodSearchReadModelFrom(foodItems)
    }

    override fun handle(fetchUserFoodsCommand: FetchUserFoodsCommand): List<FoodGeneralReadModel> {
        return foodDomainRepository.findFoodItemsByUserId(fetchUserFoodsCommand.userId)
    }

    @Transactional("foodTransactionManager")
    override fun handle(createFoodItemCommand: CreateFoodItemCommand) {
        val userFoodCatalog = foodDomainRepository.getUserFoodCatalogByUserId(createFoodItemCommand.userId)
        val foodItem = foodApplicationFactory.foodItemFrom(createFoodItemCommand)
        userFoodCatalog.addFoodItem(foodItem)
        foodDomainRepository.saveUserFoodCatalog(createFoodItemCommand.userId, userFoodCatalog)
    }

    @Transactional("foodTransactionManager")
    override fun handle(deleteFoodItemCommand: DeleteFoodItemCommand) {
        val userFoodCatalog = foodDomainRepository.getUserFoodCatalogByUserId(deleteFoodItemCommand.userId)
        userFoodCatalog.removeFoodItem(deleteFoodItemCommand.foodItemId)
        foodDomainRepository.saveUserFoodCatalog(deleteFoodItemCommand.userId, userFoodCatalog)
    }
}