package com.example.parkinglot.repositories;

import com.example.parkinglot.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    // Add custom queries if needed
    Optional<Vehicle> findByLicensePlate(String licensePlate);
}