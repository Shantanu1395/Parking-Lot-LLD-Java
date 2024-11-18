package com.example.parkinglot.repository;

import com.example.parkinglot.model.ParkingRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRateRepository extends JpaRepository<ParkingRate, Long> {
    // Add custom queries if needed
}