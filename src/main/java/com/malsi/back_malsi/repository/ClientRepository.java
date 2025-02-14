package com.malsi.back_malsi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malsi.back_malsi.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    public Optional<Client> findByEmail(String email);

    public Optional<Client> findByPhone(String phone);
}
