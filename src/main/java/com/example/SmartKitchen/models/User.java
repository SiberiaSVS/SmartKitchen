package com.example.SmartKitchen.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", nullable = false, length = 62)
    private String email;

    @Column(name = "password", nullable = false, length = 72)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "favorite_recipes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private List<Recipe> favoriteRecipes;

    @OneToMany(mappedBy = "user")
    List<AvailableIngredient> availableIngredients;

    @OneToMany(mappedBy = "user")
    List<ShoppingList> shoppingList;
}
