package com.example.parkinglot.repositories;

import com.example.parkinglot.model.ParkingRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRateRepository extends JpaRepository<ParkingRate, Long> {
    // Add custom queries if needed
    List<ParkingRate> findByParkingSpotId(Long parkingSpotId);
}