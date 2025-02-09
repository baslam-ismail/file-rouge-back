package com.malsi.back_malsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malsi.back_malsi.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
