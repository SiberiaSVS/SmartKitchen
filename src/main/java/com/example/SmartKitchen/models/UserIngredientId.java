package com.example.SmartKitchen.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class UserIngredientId implements Serializable {
    @Column(name = "user_id")
    Long userId;

    @Column(name = "ingredient_id")
    Long ingredientId;
}
