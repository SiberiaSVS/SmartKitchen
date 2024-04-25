package com.example.SmartKitchen.controllers;

import com.example.SmartKitchen.models.Role;
import com.example.SmartKitchen.models.User;
import com.example.SmartKitchen.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/secured")
@RequiredArgsConstructor
public class MainController {
    private final UserRepository userRepository;

    @GetMapping("/user")
    public String userAccess(Principal principal) {
        if (principal == null) return null;
        return principal.getName();
    }

    @GetMapping("/any")
    public String any() {
        return "Hello :3";
    }

    @GetMapping("/admin")
    public String adminAccess(Principal principal) {
        if (principal == null) return null;
        return principal.getName();
    }

    @GetMapping("/get-admin")
    public HttpStatus getAdmin(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        user.setRole(Role.ROLE_ADMIN);
        userRepository.save(user);
        return HttpStatus.OK;
    }

    @GetMapping("/ban-self")
    public HttpStatus banSelf(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        user.setEnabled(false);
        userRepository.save(user);
        return HttpStatus.OK;
    }
}
