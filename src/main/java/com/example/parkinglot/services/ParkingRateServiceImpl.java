package com.example.parkinglot.services;

import com.example.parkinglot.interfaces.ParkingRateService;
import com.example.parkinglot.model.ParkingRate;
import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.repositories.ParkingRateRepository;
import com.example.parkinglot.repositories.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingRateServiceImpl implements ParkingRateService {

    private final ParkingRateRepository parkingRateRepository;
    private final ParkingSpotRepository parkingSpotRepository;

    @Autowired
    public ParkingRateServiceImpl(ParkingRateRepository parkingRateRepository, ParkingSpotRepository parkingSpotRepository) {
        this.parkingRateRepository = parkingRateRepository;
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Override
    public ParkingRate setRate(Long parkingSpotId, String spotSize, double hourlyRate, LocalDateTime startTime, LocalDateTime endTime) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(parkingSpotId)
                .orElseThrow(() -> new IllegalArgumentException("Parking spot not found"));

        // Factory | Builder Pattern for creating ParkingRate
        ParkingRate parkingRate = new ParkingRate.Builder()
                .spotSize(spotSize)
                .hourlyRate(hourlyRate)
                .startTime(startTime)
                .endTime(endTime)
                .parkingSpot(parkingSpot)
                .build();

        return parkingRateRepository.save(parkingRate);
    }

    @Override
    public List<ParkingRate> getRatesBySpot(Long parkingSpotId) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(parkingSpotId)
                .orElseThrow(() -> new IllegalArgumentException("Parking spot not found"));

        return parkingRateRepository.findByParkingSpotId(parkingSpotId);
    }
}
