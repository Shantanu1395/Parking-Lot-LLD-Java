package com.example.parkinglot.repository;


import com.example.parkinglot.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    // Add custom queries if needed
}