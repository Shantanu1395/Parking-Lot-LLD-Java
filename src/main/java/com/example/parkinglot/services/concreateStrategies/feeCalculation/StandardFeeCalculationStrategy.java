package com.example.parkinglot.services.concreateStrategies.feeCalculation;

import com.example.parkinglot.interfaces.strategy.FeeCalculationStrategy;
import com.example.parkinglot.model.Booking;
import com.example.parkinglot.model.ParkingSpot;
import org.springframework.stereotype.Component;

@Component
public class StandardFeeCalculationStrategy implements FeeCalculationStrategy {
    @Override
    public double calculateFee(Booking booking) {

        double hourlyRate = booking.getParkingSpot().getBaseParkingRate();
        return hourlyRate * (booking.calculateDurationInHours() + 1);
    }
}

