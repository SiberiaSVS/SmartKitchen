package com.example.SmartKitchen.repositories;

import com.example.SmartKitchen.models.AvailableIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableIngredientRepository extends JpaRepository<AvailableIngredient, Long> {

}
