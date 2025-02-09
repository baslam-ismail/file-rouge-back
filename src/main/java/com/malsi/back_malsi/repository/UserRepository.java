package com.malsi.back_malsi.repository;

import com.malsi.back_malsi.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhone(String phone);

    Optional<User> findByEmail(String email);
}
