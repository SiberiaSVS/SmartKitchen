package com.example.SmartKitchen.services;

import com.example.SmartKitchen.dto.IngredientDTO;
import com.example.SmartKitchen.models.Ingredient;
import com.example.SmartKitchen.repositories.IngredientRepository;
import com.example.SmartKitchen.repositories.MeasureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final MeasureRepository measureRepository;

    public Ingredient create(IngredientDTO dto) {
        return ingredientRepository.save(Ingredient.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .imagePath(dto.getImagePath())
                .measure(measureRepository.findById(dto.getMeasureId()).orElseThrow())
                .build());
    }

    public Ingredient getById(Long id) {
        return ingredientRepository.findById(id).orElseThrow();
    }

    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    public boolean deleteById(Long id) {
        if (ingredientRepository.existsById(id)) {
            ingredientRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Ingredient update(Long id, IngredientDTO dto) {
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow();
        if (dto.getDescription() != null)
            ingredient.setDescription(dto.getDescription());
        if (dto.getImagePath() != null)
            ingredient.setImagePath(dto.getImagePath());
        if (dto.getName() != null)
            ingredient.setName(dto.getName());
        if (dto.getMeasureId() != null)
            ingredient.setMeasure(measureRepository.findById(dto.getMeasureId()).orElseThrow());
        return ingredientRepository.save(ingredient);
    }
}
