package com.example.SmartKitchen.services;

import com.example.SmartKitchen.dto.IngredientAmountDTO;
import com.example.SmartKitchen.dto.ShoppingListDTO;
import com.example.SmartKitchen.models.Ingredient;
import com.example.SmartKitchen.models.ShoppingList;
import com.example.SmartKitchen.models.User;
import com.example.SmartKitchen.models.UserIngredientId;
import com.example.SmartKitchen.repositories.IngredientRepository;
import com.example.SmartKitchen.repositories.ShoppingListRepository;
import com.example.SmartKitchen.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingListService {
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final AvailableIngredientsService availableIngredientsService;

    public ShoppingListDTO addIngredientToShoppingListForThisUser(Principal principal, Long ingredientId, float amount) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow();

        ShoppingList shoppingList = new ShoppingList(new UserIngredientId(), user, ingredient, amount);

        shoppingList = shoppingListRepository.save(shoppingList);

        return ShoppingListDTO.builder()
                .ingredient(shoppingList.getIngredient())
                .amount(shoppingList.getAmount())
                .build();
    }

    public List<ShoppingListDTO> getShoppingListForThisUser(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        List<ShoppingList> shoppingList = shoppingListRepository.findByUserId(user.getId());

        return shoppingList.stream().map(element -> ShoppingListDTO.builder()
                .ingredient(element.getIngredient())
                .amount(element.getAmount())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public void removeIngredientFromShoppingListForThisUserById(Principal principal, Long ingredientId) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        shoppingListRepository.deleteByUserIdAndIngredientId(user.getId(), ingredientId);
    }

    @Transactional
    public void markAsBought(Principal principal, Long ingredientId) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();

        ShoppingList ingredientFromShoppingList = shoppingListRepository.findByUserIdAndIngredientId(user.getId(), ingredientId);

        shoppingListRepository.deleteByUserIdAndIngredientId(user.getId(), ingredientId);
        availableIngredientsService.addIngredientToAvailableFromShoppingList(ingredientFromShoppingList);
    }
}
