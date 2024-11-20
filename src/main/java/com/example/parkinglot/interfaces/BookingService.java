package com.example.parkinglot.interfaces;

import com.example.parkinglot.interfaces.strategy.FeeCalculationStrategy;
import com.example.parkinglot.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    Booking createBooking(Long vehicleId, Long parkingSpotId, LocalDateTime startTime, LocalDateTime endTime);
    Booking endBooking(Long bookingId);
    Booking extendBooking(Long bookingId, LocalDateTime newEndTime);
    void cancelBooking(Long bookingId);
    List<Booking> getBookingsByVehicle(Long vehicleId);
    double calculateFee(Long bookingId); // Simplified to exclude strategy as parameter
}
