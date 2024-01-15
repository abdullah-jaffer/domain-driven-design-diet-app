package com.self.health.food.infrastructure.repository.hibernate.repository

import com.self.health.food.application.query.FoodGeneralReadModel
import com.self.health.food.infrastructure.repository.dao.FoodItemDAO
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FoodItemRepository: JpaRepository<FoodItemDAO, String> {
    @Query("""
                 SELECT foodItem
                 FROM FoodItemDAO foodItem 
                 LEFT JOIN FoodItemUserDAO foodUser on foodItem.id = foodUser.foodItemId
                 JOIN CategoryDAO category on foodItem.categoryId = category.id
                 WHERE (foodItem.type = com.self.health.food.domain.enum.FoodType.GENERAL 
                 OR (foodItem.type = com.self.health.food.domain.enum.FoodType.CUSTOM AND foodUser.userId = :userId))
                 AND (foodItem.description LIKE concat('%', :searchKey,'%') 
                 OR category.name LIKE concat('%', :searchKey,'%'))
                 AND foodItem.status = com.self.health.food.domain.enum.ItemStatus.ACTIVE
          """
    )
    fun findFoodItems(searchKey: String, userId: String, pageable: Pageable): List<FoodItemDAO>

    @Query("""
                 SELECT new com.self.health.food.application.query.FoodGeneralReadModel(
                 foodItem.id,foodItem.description, foodItem.quantity, foodItem.unit, foodItem.image)
                 FROM FoodItemDAO foodItem 
                 JOIN FoodItemUserDAO foodUser on foodItem.id = foodUser.foodItemId
                 WHERE foodUser.userId = :userId
                 AND foodItem.status = com.self.health.food.domain.enum.ItemStatus.ACTIVE
          """
    )
    fun findFoodItemsReadModelByUserId(userId: String, pageable: Pageable): List<FoodGeneralReadModel>

    @Query("""
                 SELECT foodItem
                 FROM FoodItemDAO foodItem 
                 JOIN FoodItemUserDAO foodUser on foodItem.id = foodUser.foodItemId
                 WHERE foodUser.userId = :userId
                 AND foodItem.status = com.self.health.food.domain.enum.ItemStatus.ACTIVE
          """
    )
    fun findFoodItemsByUserId(userId: String, pageable: Pageable): List<FoodItemDAO>
}