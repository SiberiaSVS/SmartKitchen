package com.example.SmartKitchen.controllers;

import com.example.SmartKitchen.dto.RecipeDTO;
import com.example.SmartKitchen.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public ResponseEntity<?> getAll(Principal principal) {
        return ResponseEntity.ok(recipeService.getAllRecipes(principal));
    }

    @PostMapping
    public ResponseEntity<?> addRecipe(Principal principal, @RequestBody RecipeDTO dto) {
        return ResponseEntity.ok(recipeService.create(principal, dto));
    }

    @GetMapping("/add-to-favorites/{id}")
    public ResponseEntity<?> addRecipeToFavorites(Principal principal, @PathVariable Long id) {
        return ResponseEntity.ok(recipeService.addRecipeToFavorite(principal, id));
    }

    @GetMapping("/remove-from-favorites/{id}")
    public ResponseEntity<?> removeRecipeFromFavorites(Principal principal, @PathVariable Long id) {
        return ResponseEntity.ok(recipeService.removeRecipeFromFavorite(principal, id));
    }
}
