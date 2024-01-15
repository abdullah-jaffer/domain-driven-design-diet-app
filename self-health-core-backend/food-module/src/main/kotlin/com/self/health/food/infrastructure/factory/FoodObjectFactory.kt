package com.self.health.food.infrastructure.factory

import com.self.health.food.domain.aggregate.UserFoodCatalog
import com.self.health.food.domain.entity.FoodItem
import com.self.health.food.domain.valueobject.Fats
import com.self.health.food.domain.valueobject.FoodDescription
import com.self.health.food.domain.valueobject.FoodMeasurementUnit
import com.self.health.food.domain.valueobject.MacroNutrients
import com.self.health.food.domain.valueobject.MicroNutrients
import com.self.health.food.infrastructure.repository.dao.FoodItemDAO
import com.self.health.food.infrastructure.repository.dao.FoodItemUserDAO
import org.springframework.stereotype.Component

@Component
class FoodObjectFactory {

    fun foodItemsFrom(foodItemsFromDB: List<FoodItemDAO>): List<FoodItem> {
        return foodItemsFromDB.map {
            FoodItem(
                it.id,
                FoodDescription(it.description, it.nutritionBankNumber, it.image),
                MacroNutrients(it.carbohydrates, it.protein, it.sugar),
                MicroNutrients(it.cholesterol),
                Fats(it.monoSaturatedFat, it.polySaturatedFat, it.saturatedFat),
                FoodMeasurementUnit(it.quantity),
                it.type,
                it.categoryId,
                it.status
            )
        }
    }

    fun userFoodCatalogFrom(userId: String, foodItemsDAO: List<FoodItemDAO>): UserFoodCatalog {
        val foodItems = foodItemsFrom(foodItemsDAO)
        return UserFoodCatalog(userId, foodItems)
    }

    fun daoEntitiesFromUserFoodCatalog(userId: String, userFoodCatalog: UserFoodCatalog): Pair<List<FoodItemUserDAO>, List<FoodItemDAO>> {
        val foodUserDaoList = foodUserDaoItemsFrom(userId, userFoodCatalog.foodItems())
        val foodItemDaoList = foodItemDaoListFrom(userFoodCatalog.foodItems())


        return Pair(foodUserDaoList, foodItemDaoList)
    }

    fun foodUserDaoItemsFrom(userId: String, foodItem: List<FoodItem>): List<FoodItemUserDAO> {
        return foodItem.map {
            FoodItemUserDAO(null, userId, it.id)
        }
    }

    fun foodItemDaoListFrom(foodItems: List<FoodItem>): List<FoodItemDAO> {
        return foodItems.map {
            FoodItemDAO(
                it.id,
                it.foodDescription.description,
                it.foodDescription.nutritionBankNumber,
                it.macroNutrients.carbohydrates,
                it.microNutrients.cholesterol,
                it.macroNutrients.protein,
                it.macroNutrients.sugar,
                it.fats.monoSaturatedFat,
                it.fats.polySaturatedFat,
                it.fats.saturatedFat,
                it.foodType,
                it.foodMeasurementUnit.quantity,
                it.foodMeasurementUnit.unit.name,
                it.foodDescription.image,
                it.categoryId,
                it.status
            )
        }
    }
}