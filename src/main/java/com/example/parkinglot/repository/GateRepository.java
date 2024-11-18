package com.example.parkinglot.repository;

import com.example.parkinglot.model.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateRepository extends JpaRepository<Gate, Long> {
    // Add custom queries if needed
}