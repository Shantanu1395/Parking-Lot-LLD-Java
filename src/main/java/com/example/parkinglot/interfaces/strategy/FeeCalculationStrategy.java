package com.example.parkinglot.interfaces.strategy;

import com.example.parkinglot.model.Booking;
import com.example.parkinglot.model.ParkingSpot;

public interface FeeCalculationStrategy {
    double calculateFee(Booking booking);
}
