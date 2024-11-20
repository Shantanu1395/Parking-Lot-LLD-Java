package com.example.parkinglot.services.concreateStrategies.feeCalculation;

import com.example.parkinglot.interfaces.strategy.FeeCalculationStrategy;
import com.example.parkinglot.model.ParkingRate;
import com.example.parkinglot.model.ParkingSpot;

public class StandardFeeCalculationStrategy implements FeeCalculationStrategy {
    @Override
    public double calculateFee(ParkingSpot parkingSpot, long durationInHours) {
        double hourlyRate = parkingSpot.getParkingRates().get(0).getHourlyRate();
        return hourlyRate * durationInHours;
    }
}

