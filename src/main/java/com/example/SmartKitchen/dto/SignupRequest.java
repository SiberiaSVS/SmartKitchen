package com.example.SmartKitchen.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    @Email(message = "Неверный email")
    private String email;
    @NotNull
    @NotBlank
    private String password;
}
