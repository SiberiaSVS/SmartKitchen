package com.example.SmartKitchen.services;

import com.example.SmartKitchen.dto.UserDTO;
import com.example.SmartKitchen.models.Role;
import com.example.SmartKitchen.models.User;
import com.example.SmartKitchen.repositories.UserRepository;
import com.example.SmartKitchen.security.UserDetailsImpl;
import com.example.SmartKitchen.util.PasswordsNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserDetailsImpl.build(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    public boolean banUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return false;
        user.setEnabled(false);
        userRepository.save(user);
        return true;
    }

    public boolean unbanUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return false;
        user.setEnabled(true);
        userRepository.save(user);
        return true;
    }

    public boolean setRoleAdmin(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return false;
        user.setRole(Role.ROLE_ADMIN);
        userRepository.save(user);
        return true;
    }

    public boolean setRoleUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return false;
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        return true;
    }

    public User getUserByPrincipal(Principal principal) {
        return userRepository.findByUsername(principal.getName()).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateThisUser(Principal principal, UserDTO dto) throws PasswordsNotMatchException {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        if (dto.getUsername() != null)
            user.setUsername(dto.getUsername());
        if (dto.getEmail() != null)
            user.setEmail(dto.getEmail());
        if (dto.getPassword() != null) {
            if (passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(dto.getPassword()));
            } else {
                throw new PasswordsNotMatchException("Неверный старый пароль");
            }
        }
        return userRepository.save(user);
    }
}
