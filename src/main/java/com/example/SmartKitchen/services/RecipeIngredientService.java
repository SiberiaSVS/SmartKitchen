package com.example.SmartKitchen.services;

import com.example.SmartKitchen.dto.IngredientAmountDTO;
import com.example.SmartKitchen.models.RecipeIngredient;
import com.example.SmartKitchen.models.RecipeIngredientId;
import com.example.SmartKitchen.repositories.IngredientRepository;
import com.example.SmartKitchen.repositories.RecipeIngredientRepository;
import com.example.SmartKitchen.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeIngredientService {
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    @Transactional
    public List<RecipeIngredient> updateIngredientsListInRecipe(List<IngredientAmountDTO> ingredientAmountDTOList, Long recipeId) {
        recipeIngredientRepository.deleteByRecipeId(recipeId);

        return ingredientAmountDTOList.stream().map(e -> recipeIngredientRepository.save(
                new RecipeIngredient(
                        new RecipeIngredientId(),
                        recipeRepository.findById(recipeId).orElseThrow(),
                        ingredientRepository.findById(e.getIngredientId()).orElseThrow(),
                        e.getAmount())
        )).collect(Collectors.toList());
    }
}
