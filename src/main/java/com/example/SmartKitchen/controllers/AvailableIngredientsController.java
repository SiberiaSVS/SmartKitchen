package com.example.SmartKitchen.controllers;

import com.example.SmartKitchen.dto.IngredientAmountDTO;
import com.example.SmartKitchen.services.AvailableIngredientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/available-ingredients")
public class AvailableIngredientsController {
    private final AvailableIngredientsService availableIngredientsService;

    @PostMapping
    public ResponseEntity<?> addIngredientToAvailable(Principal principal, @RequestBody IngredientAmountDTO dto) {
        return ResponseEntity.ok(availableIngredientsService.addIngredientToAvailableForThisUser(principal, dto.getIngredientId(), dto.getAmount()));
    }

    @GetMapping
    public ResponseEntity<?> getAvailableIngredients(Principal principal) {
        return ResponseEntity.ok(availableIngredientsService.getAvailableIngredientsForThisUser(principal));
    }

    @PatchMapping
    public ResponseEntity<?> update(Principal principal, @RequestBody IngredientAmountDTO dto) {
        return ResponseEntity.ok(availableIngredientsService.updateAvailableIngredient(principal, dto.getIngredientId(), dto.getAmount()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredientFromAvailableIngredients(Principal principal, @PathVariable Long id) {
        availableIngredientsService.removeIngredientFromAvailableIngredientsForThisUser(principal, id);
        return ResponseEntity.ok("Ok");
    }
}
