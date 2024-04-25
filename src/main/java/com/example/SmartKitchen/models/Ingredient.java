package com.example.SmartKitchen.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ingredient_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image_path", nullable = false, length = 50)
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "measure_id", nullable = false)
    private Measure measure;

    @OneToMany(mappedBy = "ingredient")
    private List<RecipeIngredient> recipes;
}
