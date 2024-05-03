package com.example.SmartKitchen.services;

import com.example.SmartKitchen.dto.AvailableIngredientDTO;
import com.example.SmartKitchen.models.*;
import com.example.SmartKitchen.repositories.AvailableIngredientRepository;
import com.example.SmartKitchen.repositories.IngredientRepository;
import com.example.SmartKitchen.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableIngredientsService {
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final AvailableIngredientRepository availableIngredientRepository;

    public AvailableIngredientDTO addIngredientToAvailableForThisUser(Principal principal, Long ingredientId, float amount) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow();

        AvailableIngredient availableIngredient = new AvailableIngredient(new UserIngredientId(), user, ingredient, amount);

        availableIngredient = availableIngredientRepository.save(availableIngredient);

        return AvailableIngredientDTO.builder()
                .ingredient(availableIngredient.getIngredient())
                .amount(availableIngredient.getAmount())
                .build();
    }

    public AvailableIngredientDTO updateAvailableIngredient(Principal principal, Long ingredientId, float amount) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        AvailableIngredient availableIngredient = availableIngredientRepository.findByUserIdAndIngredientId(user.getId(), ingredientId);

        availableIngredient.setAmount(amount);
        availableIngredient = availableIngredientRepository.save(availableIngredient);
        return AvailableIngredientDTO.builder()
                .ingredient(availableIngredient.getIngredient())
                .amount(availableIngredient.getAmount())
                .build();
    }

    public AvailableIngredientDTO addIngredientToAvailableFromShoppingList(ShoppingList ingredientFromShoppingList) {
        User user = ingredientFromShoppingList.getUser();
        Ingredient ingredient = ingredientFromShoppingList.getIngredient();
        float amount = ingredientFromShoppingList.getAmount();

        AvailableIngredient availableIngredient;

        if (availableIngredientRepository.existsByUserIdAndIngredientId(user.getId(), ingredient.getId())) {
            availableIngredient = availableIngredientRepository.findByUserIdAndIngredientId(user.getId(), ingredient.getId());

            availableIngredient.setAmount(availableIngredient.getAmount() + amount);
        } else {
            availableIngredient = new AvailableIngredient(new UserIngredientId(), user, ingredient, amount);
        }

        availableIngredient = availableIngredientRepository.save(availableIngredient);

        return AvailableIngredientDTO.builder()
                .ingredient(availableIngredient.getIngredient())
                .amount(availableIngredient.getAmount())
                .build();
    }

    public List<AvailableIngredientDTO> getAvailableIngredientsForThisUser(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        List<AvailableIngredient> availableIngredients = availableIngredientRepository.findByUserId(user.getId());

        return availableIngredients.stream().map(element -> AvailableIngredientDTO.builder()
                .ingredient(element.getIngredient())
                .amount(element.getAmount())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public void removeIngredientFromAvailableIngredientsForThisUser(Principal principal, Long ingredientId) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        availableIngredientRepository.deleteByUserIdAndIngredientId(user.getId(), ingredientId);
    }
}
