package com.example.SmartKitchen.dto;

import com.example.SmartKitchen.models.Tag;
import com.example.SmartKitchen.models.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeResponseDTO {
    private Long id;
    private User user;
    private String name;
    private String imagePath;
    private String description;
    private String cookSteps;
    private boolean hiddenForOthers;

    private List<Tag> tags;
    private List<IngredientAmountResponseDTO> ingredients;
}
