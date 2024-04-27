package com.example.SmartKitchen.controllers;

import com.example.SmartKitchen.dto.UserDTO;
import com.example.SmartKitchen.models.User;
import com.example.SmartKitchen.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Вы не авторизованы");
        }
    }

    @GetMapping("/authorized")
    public String userAccess(Principal principal) {
        if (principal == null) return null;
        return "Привет, пользователь";
    }

    @GetMapping("/any")
    public String any() {
        return "Hello :3";
    }

    @GetMapping("/admin")
    public String adminAccess(Principal principal) {
        if (principal == null) return null;
        return "Привет, админ";
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PatchMapping("/update/current")
    public ResponseEntity<?> updateThisUser(Principal principal, @Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.updateThisUser(principal, dto));
    }

    @PatchMapping("/ban/{id}")
    public ResponseEntity<?> banUserById(@PathVariable Long id) {
        if (userService.banUserById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не найден пользователь с id: " + id);
    }

    @PatchMapping("/unban/{id}")
    public ResponseEntity<?> unbanUserById(@PathVariable Long id) {
        if (userService.unbanUserById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не найден пользователь с id: " + id);
    }

    @PatchMapping("/set-role/admin/{id}")
    public ResponseEntity<?> setRoleAdminById(@PathVariable Long id) {
        if (userService.setRoleAdmin(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не найден пользователь с id: " + id);
    }

    @PatchMapping("/set-role/user/{id}")
    public ResponseEntity<?> setRoleUserById(@PathVariable Long id) {
        if (userService.setRoleUser(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не найден пользователь с id: " + id);
    }
}
