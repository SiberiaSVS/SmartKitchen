package com.example.SmartKitchen.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientAmountDTO {
    private Long ingredientId;
    private float amount;
}
