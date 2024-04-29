package com.example.SmartKitchen.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDTO {
    private String name;
    private String imagePath;
    private String description;
    private String cookSteps;
    private boolean hiddenForOthers;
    private List<String> tags;
    private List<IngredientAmountDTO> ingredients;
}
