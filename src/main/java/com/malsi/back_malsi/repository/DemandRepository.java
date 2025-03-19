package com.malsi.back_malsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malsi.back_malsi.model.Demand;

public interface DemandRepository extends JpaRepository<Demand, Integer> {
    
}
