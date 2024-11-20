package com.example.parkinglot.interfaces.strategy;

import com.example.parkinglot.model.ParkingSpot;

public interface FeeCalculationStrategy {
    double calculateFee(ParkingSpot parkingSpot, long durationInHours);
}
