package com.example.SmartKitchen.services;

import com.example.SmartKitchen.repositories.UserRepository;
import com.example.SmartKitchen.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserDetailsImpl.build(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
