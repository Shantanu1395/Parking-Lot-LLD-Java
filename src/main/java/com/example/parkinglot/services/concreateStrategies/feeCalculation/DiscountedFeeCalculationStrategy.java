package com.example.parkinglot.services.concreateStrategies.feeCalculation;

import com.example.parkinglot.interfaces.strategy.FeeCalculationStrategy;
import com.example.parkinglot.model.Booking;
import com.example.parkinglot.model.ParkingSpot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.print.Book;

@Component
public class DiscountedFeeCalculationStrategy implements FeeCalculationStrategy {

    private final double discountPercentage;

    public DiscountedFeeCalculationStrategy(@Value("${fee.discount.percentage}") double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double calculateFee(Booking booking) {
        double hourlyRate = booking.getParkingSpot().getBaseParkingRate();
        double originalFee = hourlyRate * booking.calculateDurationInHours();
        return originalFee - (originalFee * discountPercentage / 100);
    }
}
