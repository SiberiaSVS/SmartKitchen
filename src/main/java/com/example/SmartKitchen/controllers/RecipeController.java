package com.example.SmartKitchen.controllers;

import com.example.SmartKitchen.dto.RecipeDTO;
import com.example.SmartKitchen.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipeById(Principal principal, @PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(principal, id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(Principal principal, @RequestParam(name = "search", required = false) String search) {
        return ResponseEntity.ok(recipeService.getAllRecipes(principal, search));
    }

    @GetMapping("/find-with-tags")
    public ResponseEntity<?> findWithTags(Principal principal, @RequestParam(name = "tags", required = false) List<String> tags, @RequestParam(name = "search", required = false) String search) {
        return ResponseEntity.ok(recipeService.getRecipesByTags(principal, tags, search));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/find-by-available-and-tags")
    public ResponseEntity<?> findByAvailableAndTags(Principal principal, @RequestParam(name = "tags", required = false) List<String> tags, @RequestParam(name = "search", required = false) String search) {
        return ResponseEntity.ok(recipeService.getRecipesByTagsAndAvailableProducts(principal, tags, search));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipeById(Principal principal, @PathVariable Long id) {
        recipeService.deleteById(principal, id);
        return ResponseEntity.ok("Ok");
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping
    public ResponseEntity<?> addRecipe(Principal principal, @RequestBody RecipeDTO dto) {
        return ResponseEntity.ok(recipeService.create(principal, dto));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRecipe(Principal principal, @PathVariable Long id, @RequestBody RecipeDTO dto) {
        return ResponseEntity.ok(recipeService.updateRecipe(principal, id, dto));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/add-to-favorites/{id}")
    public ResponseEntity<?> addRecipeToFavorites(Principal principal, @PathVariable Long id) {
        return ResponseEntity.ok(recipeService.addRecipeToFavorite(principal, id));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/remove-from-favorites/{id}")
    public ResponseEntity<?> removeRecipeFromFavorites(Principal principal, @PathVariable Long id) {
        return ResponseEntity.ok(recipeService.removeRecipeFromFavorite(principal, id));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PatchMapping("/mark-cooked/{id}")
    public ResponseEntity<?> markCooked(Principal principal, @PathVariable Long id) {
        recipeService.markCooked(principal, id);
        return ResponseEntity.ok("Ok");
    }
}
