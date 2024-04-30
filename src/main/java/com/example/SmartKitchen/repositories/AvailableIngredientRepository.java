package com.example.SmartKitchen.repositories;

import com.example.SmartKitchen.models.AvailableIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableIngredientRepository extends JpaRepository<AvailableIngredient, Long> {
    void deleteByUserIdAndIngredientId(@Param("userId") Long userId, @Param("ingredientId") Long ingredientId);
    List<AvailableIngredient> findByUserId(Long userId);
    AvailableIngredient findByUserIdAndIngredientId(Long userId, Long ingredientId);
    boolean existsByUserIdAndIngredientId(Long userId, Long ingredientId);
}
