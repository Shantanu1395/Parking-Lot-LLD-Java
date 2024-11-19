package com.example.parkinglot.interfaces;

import com.example.parkinglot.model.ParkingSpot;

import java.util.List;

public interface ParkingSpotService {
    ParkingSpot createSpot(String spotSize, Long levelId);
    ParkingSpot assignSpotToVehicle(Long parkingSpotId, Long vehicleId);
    void releaseSpot(Long parkingSpotId);
    List<ParkingSpot> getAvailableSpots(String spotSize, Long levelId);
}
