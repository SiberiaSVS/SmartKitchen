package com.example.SmartKitchen.repositories;

import com.example.SmartKitchen.models.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    //@Query(value = "DELETE FROM shopping_list sl WHERE sl.user_id = :userId AND sl.ingredient_id = :ingredientId", nativeQuery = true)
    void deleteByUserIdAndIngredientId(@Param("userId") Long userId, @Param("ingredientId") Long ingredientId);

    //@Query(value = "SELECT * FROM shopping_list sl WHERE sl.user_id = :userId", nativeQuery = true)
    List<ShoppingList> findByUserId(Long userId);
}
