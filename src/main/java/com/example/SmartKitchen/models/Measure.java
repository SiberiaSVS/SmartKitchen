package com.example.SmartKitchen.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "measures")
public class Measure {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "measure_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 10)
    private String name;
}
