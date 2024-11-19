package com.example.parkinglot.interfaces;

import com.example.parkinglot.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    Booking createBooking(Long vehicleId, Long parkingSpotId, LocalDateTime startTime, LocalDateTime endTime);
    Booking extendBooking(Long bookingId, LocalDateTime newEndTime);
    List<Booking> getBookingsByVehicle(Long vehicleId);
}
