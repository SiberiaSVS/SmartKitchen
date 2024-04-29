package com.example.SmartKitchen.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tags", indexes = @Index(columnList = "name"))
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tag_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50, unique = true)

    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Recipe> recipe;
}
