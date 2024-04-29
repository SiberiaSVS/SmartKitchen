package com.example.SmartKitchen.dto;

import com.example.SmartKitchen.models.Ingredient;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvailableIngredientDTO {
    private Ingredient ingredient;
    private float amount;
}
