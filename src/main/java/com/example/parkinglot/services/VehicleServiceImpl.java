package com.example.parkinglot.services;

import com.example.parkinglot.interfaces.VehicleService;
import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.repositories.VehicleRepository;
import com.example.parkinglot.interfaces.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle registerVehicle(String licensePlate, String type) {
        // Use the static factory method
        Vehicle vehicle = Vehicle.create(licensePlate, type);

        // Save to the repository
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + vehicleId));
    }
}
