package com.example.parkinglot.repositories;


import com.example.parkinglot.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    // Add custom queries if needed
}
