package com.example.parkinglot.services;

import com.example.parkinglot.interfaces.ParkingSpotService;
import com.example.parkinglot.interfaces.VehicleService;
import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.repositories.VehicleRepository;
import com.example.parkinglot.interfaces.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingSpotService parkingSpotService;

    @Override
    public Vehicle registerVehicle(String licensePlate, String vehicleType) {

        Vehicle vehicle = vehicleRepository.findByLicensePlate(licensePlate).orElse(Vehicle.create(licensePlate, vehicleType));
        return vehicleRepository.save(vehicle);
    }
}
