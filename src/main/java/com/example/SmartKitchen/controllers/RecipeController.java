package com.example.SmartKitchen.controllers;

import com.example.SmartKitchen.dto.RecipeDTO;
import com.example.SmartKitchen.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public ResponseEntity<?> getAll(Principal principal) {
        return ResponseEntity.ok(recipeService.getAllRecipes(principal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipeById(Principal principal, @PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(principal, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipeById(Principal principal, @PathVariable Long id) {
        recipeService.deleteById(principal, id);
        return ResponseEntity.ok("Ok");
    }

    @PostMapping
    public ResponseEntity<?> addRecipe(Principal principal, @RequestBody RecipeDTO dto) {
        return ResponseEntity.ok(recipeService.create(principal, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRecipe(Principal principal, @PathVariable Long id, @RequestBody RecipeDTO dto) {
        return ResponseEntity.ok(recipeService.updateRecipe(principal, id, dto));
    }

    @GetMapping("/add-to-favorites/{id}")
    public ResponseEntity<?> addRecipeToFavorites(Principal principal, @PathVariable Long id) {
        return ResponseEntity.ok(recipeService.addRecipeToFavorite(principal, id));
    }

    @GetMapping("/remove-from-favorites/{id}")
    public ResponseEntity<?> removeRecipeFromFavorites(Principal principal, @PathVariable Long id) {
        return ResponseEntity.ok(recipeService.removeRecipeFromFavorite(principal, id));
    }

    @PatchMapping("/mark-cooked/{id}")
    public ResponseEntity<?> markCooked(Principal principal, @PathVariable Long id) {
        recipeService.markCooked(principal, id);
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/find-with-tags")
    public ResponseEntity<?> findWithTags(Principal principal, @RequestBody List<String> tags) {
        return ResponseEntity.ok(recipeService.getRecipesByTags(principal, tags));
    }

    @GetMapping("/find-by-available-and-tags")
    public ResponseEntity<?> findByAvailableAndTags(Principal principal, @RequestBody List<String> tags) {
        return ResponseEntity.ok(recipeService.getRecipesByTagsAndAvailableProducts(principal, tags));
    }
}
