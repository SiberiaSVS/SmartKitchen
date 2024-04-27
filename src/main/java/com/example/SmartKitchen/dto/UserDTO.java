package com.example.SmartKitchen.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {
    private String username;

    @Email
    private String email;

    private String oldPassword;

    private String password;
}
