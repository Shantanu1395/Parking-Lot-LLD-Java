package com.example.parkinglot.repositories;


import com.example.parkinglot.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    // Add custom queries if needed
    List<Level> findByParkingLotId(Long parkingLotId);
}