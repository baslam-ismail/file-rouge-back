package com.malsi.back_malsi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.malsi.back_malsi.filter.EmailPasswordAuthenticationFilter;
import com.malsi.back_malsi.filter.JwtFilter;
import com.malsi.back_malsi.service.CustomUserDetailsService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private final JwtUtils jwtUtils;
    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity htpp) throws Exception {
        return htpp.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> 
        auth.requestMatchers("/api/auth/*", "/swagger-ui/*", "/api-docs/*", "/api-docs").permitAll().anyRequest().authenticated()
        ).addFilterBefore(new JwtFilter(customUserDetailsService, jwtUtils), EmailPasswordAuthenticationFilter.class)
        .build();
    }
}
