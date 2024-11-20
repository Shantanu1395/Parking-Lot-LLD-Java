package com.example.parkinglot.interfaces;

import com.example.parkinglot.model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle registerVehicle(String licensePlate, String type);
    Vehicle getVehicleById(Long vehicleId);
}
