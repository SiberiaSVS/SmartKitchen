package com.example.SmartKitchen.services;

import com.example.SmartKitchen.dto.IngredientAmountDTO;
import com.example.SmartKitchen.models.RecipeIngredient;
import com.example.SmartKitchen.models.RecipeIngredientId;
import com.example.SmartKitchen.repositories.IngredientRepository;
import com.example.SmartKitchen.repositories.RecipeIngredientRepository;
import com.example.SmartKitchen.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeIngredientService {
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public void updateIngredientsListInRecipe(List<IngredientAmountDTO> ingredientAmountDTOList, Long recipeId) {
        recipeIngredientRepository.deleteByRecipeId(recipeId);

        for(IngredientAmountDTO e : ingredientAmountDTOList) {
            recipeIngredientRepository.save(
                new RecipeIngredient(
                        new RecipeIngredientId(),
                        recipeRepository.findById(recipeId).orElseThrow(),
                        ingredientRepository.findById(e.getIngredientId()).orElseThrow(),
                        e.getAmount())
            );
        }
    }
}
