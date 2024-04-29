package com.example.SmartKitchen.dto;

import lombok.Data;

@Data
public class IngredientDTO {
    private String name;
    private String description;
    private String imagePath;
    private Long measureId;
}
