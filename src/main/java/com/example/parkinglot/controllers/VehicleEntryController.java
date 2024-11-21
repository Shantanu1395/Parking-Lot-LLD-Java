package com.example.parkinglot.controllers;

import com.example.parkinglot.interfaces.BookingService;
import com.example.parkinglot.interfaces.ParkingSpotService;
import com.example.parkinglot.interfaces.VehicleService;
import com.example.parkinglot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/entry")
public class VehicleEntryController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ParkingSpotService spotService;

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> vehicleEntry(@RequestParam String licensePlate,
                                          @RequestParam String vehicleType,
                                          @RequestParam Double hourlyRate) {

        Vehicle vehicle = vehicleService.registerVehicle(licensePlate, vehicleType);
        ParkingSpot availableSpot = spotService.assignSpotToVehicle(vehicle);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(1); // Example end time (adjust as needed)

        Booking booking = bookingService.createBooking(vehicle, availableSpot.getSpotId(), startTime, endTime);

        return ResponseEntity.ok(booking.getId());
    }
}