package com.example.parkinglot.interfaces.strategy;

import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.Vehicle;

public interface SpotAssignmentStrategy {
    ParkingSpot assignSpot(Vehicle vehicle, Long levelId);
}
