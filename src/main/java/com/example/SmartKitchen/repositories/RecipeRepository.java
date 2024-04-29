package com.example.SmartKitchen.repositories;

import com.example.SmartKitchen.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByUserIdOrHiddenForOthersFalse(Long userId);
}
