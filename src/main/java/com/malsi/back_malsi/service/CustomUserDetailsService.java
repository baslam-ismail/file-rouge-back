package com.malsi.back_malsi.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.malsi.back_malsi.model.CustomUserDetails;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found wity email: " + email);
        }

        return new CustomUserDetails(user.get().getEmail(), user.get().getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.get().getRole())));
    }
}
