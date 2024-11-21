package com.example.parkinglot.interfaces;

import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.Vehicle;

import java.util.List;

public interface ParkingSpotService {
    ParkingSpot createSpot(String spotSize, Long levelId);
    ParkingSpot assignSpotToVehicle(Vehicle vehicle);
    void releaseSpot(Long parkingSpotId);
}
