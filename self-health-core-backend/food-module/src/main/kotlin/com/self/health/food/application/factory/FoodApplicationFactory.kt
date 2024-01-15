package com.self.health.food.application.factory

import com.devskiller.friendly_id.FriendlyId
import com.self.health.food.application.command.CreateFoodItemCommand
import com.self.health.food.application.query.FoodSearchReadModel
import com.self.health.food.domain.entity.FoodItem
import com.self.health.food.domain.enum.FoodType.CUSTOM
import com.self.health.food.domain.enum.ItemStatus.ACTIVE
import com.self.health.food.domain.valueobject.Fats
import com.self.health.food.domain.valueobject.FoodDescription
import com.self.health.food.domain.valueobject.FoodMeasurementUnit
import com.self.health.food.domain.valueobject.MacroNutrients
import com.self.health.food.domain.valueobject.MicroNutrients
import org.springframework.stereotype.Component

@Component
class FoodApplicationFactory {
        fun foodSearchReadModelFrom(foodItems: List<FoodItem>): List<FoodSearchReadModel> {
            return foodItems.map {
                FoodSearchReadModel(
                    it.id,
                    it.foodDescription.description,
                    it.calories(),
                    it.foodMeasurementUnit.quantity,
                    it.foodMeasurementUnit.unit,
                    it.foodDescription.image
                )
            }
        }

        fun foodItemFrom(createFoodItemCommand: CreateFoodItemCommand): FoodItem {
            return FoodItem(
                FriendlyId.createFriendlyId(),
                FoodDescription(createFoodItemCommand.description, "", createFoodItemCommand.image),
                MacroNutrients(createFoodItemCommand.carbohydrates, createFoodItemCommand.protein, 0.0),
                MicroNutrients(0),
                Fats(createFoodItemCommand.fat, 0.0, 0.0),
                FoodMeasurementUnit(createFoodItemCommand.quantity),
                CUSTOM,
                createFoodItemCommand.categoryId,
                ACTIVE
            )
        }
}