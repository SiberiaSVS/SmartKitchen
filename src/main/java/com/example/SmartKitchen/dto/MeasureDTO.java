package com.example.SmartKitchen.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MeasureDTO {
    @NotNull
    @NotBlank
    private String name;
}
