package com.example.SmartKitchen.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class IngredientAmountDTO {
    private Long ingredientId;
    private float amount;
}
