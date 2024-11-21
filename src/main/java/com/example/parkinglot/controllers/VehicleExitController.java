package com.example.parkinglot.controllers;

import com.example.parkinglot.interfaces.BookingService;
import com.example.parkinglot.interfaces.ParkingSpotService;
import com.example.parkinglot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/api/exit")
public class VehicleExitController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ParkingSpotService spotService;

    @PostMapping("/endBooking")
    public ResponseEntity<Double> endBooking(@RequestParam Long bookingId) {
        Booking booking = bookingService.findById(bookingId);

        if (booking == null) {
            return ResponseEntity.badRequest().body(null);
        }

        LocalDateTime endTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault());
        booking.setEndTime(endTime);
        bookingService.endBooking(booking.getId());

        double fee = bookingService.calculateFee(booking);
        return ResponseEntity.ok(fee);
    }
}