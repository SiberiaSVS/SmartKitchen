package com.example.SmartKitchen.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "recipe_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    //@Lob
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    //@Lob
    @Column(name = "cook_steps", nullable = false, columnDefinition = "TEXT")
    private String cookSteps;

    @Column(name = "hidden_for_others", nullable = false)
    private boolean hiddenForOthers;

    @ManyToMany
    @JoinTable(
            name = "recipe_tags",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToMany(mappedBy = "favoriteRecipes")
    private List<User> usersAddedToFavorite;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER)
    private List<RecipeIngredient> ingredients;
}
