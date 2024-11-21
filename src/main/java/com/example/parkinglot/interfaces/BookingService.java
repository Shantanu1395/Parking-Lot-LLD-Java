package com.example.parkinglot.interfaces;

import com.example.parkinglot.interfaces.strategy.FeeCalculationStrategy;
import com.example.parkinglot.model.Booking;
import com.example.parkinglot.model.Vehicle;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    Booking createBooking(Vehicle vehicle, Long parkingSpotId, LocalDateTime startTime, LocalDateTime endTime);
    Booking endBooking(Long bookingId);
    double calculateFee(Booking booking); // Simplified to exclude strategy as parameter
    Booking findById(Long bookingId);
}
