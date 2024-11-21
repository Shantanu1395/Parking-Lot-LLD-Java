package com.example.parkinglot.interfaces;

import com.example.parkinglot.model.ParkingRate;

import java.time.LocalDateTime;
import java.util.List;

public interface ParkingRateService {
    ParkingRate setRate(Long parkingSpotId, String spotSize, double hourlyRate, LocalDateTime startTime, LocalDateTime endTime);
}