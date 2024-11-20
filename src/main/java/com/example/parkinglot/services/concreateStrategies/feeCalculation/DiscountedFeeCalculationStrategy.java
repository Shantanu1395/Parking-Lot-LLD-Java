package com.example.parkinglot.services.concreateStrategies.feeCalculation;

import com.example.parkinglot.interfaces.strategy.FeeCalculationStrategy;
import com.example.parkinglot.model.ParkingRate;
import com.example.parkinglot.model.ParkingSpot;

import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.interfaces.strategy.FeeCalculationStrategy;

public class DiscountedFeeCalculationStrategy implements FeeCalculationStrategy {

    private final double discountPercentage;

    public DiscountedFeeCalculationStrategy(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double calculateFee(ParkingSpot parkingSpot, long durationInHours) {
        double hourlyRate = parkingSpot.getParkingRates().get(0).getHourlyRate();
        double originalFee = hourlyRate * durationInHours;
        return originalFee - (originalFee * discountPercentage / 100);
    }
}
