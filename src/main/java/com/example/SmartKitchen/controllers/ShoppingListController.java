package com.example.SmartKitchen.controllers;

import com.example.SmartKitchen.dto.IngredientAmountDTO;
import com.example.SmartKitchen.services.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping-list")
public class ShoppingListController {
    private final ShoppingListService shoppingListService;

    @PostMapping
    public ResponseEntity<?> addIngredientToShoppingList(Principal principal, @RequestBody IngredientAmountDTO dto) {
        return ResponseEntity.ok(shoppingListService.addIngredientToShoppingListForThisUser(principal, dto.getIngredientId(), dto.getAmount()));
    }

    @GetMapping
    public ResponseEntity<?> getShoppingList(Principal principal) {
        return ResponseEntity.ok(shoppingListService.getShoppingListForThisUser(principal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredientFromShoppingList(Principal principal, @PathVariable Long id) {
        shoppingListService.removeIngredientFromShoppingListForThisUserById(principal, id);
        return ResponseEntity.ok("Ok");
    }

    @PatchMapping("/mark-as-bought/{id}")
    public ResponseEntity<?> markAsBought(Principal principal, @PathVariable Long id) {
        shoppingListService.markAsBought(principal, id);
        return ResponseEntity.ok("Ok");
    }
}
