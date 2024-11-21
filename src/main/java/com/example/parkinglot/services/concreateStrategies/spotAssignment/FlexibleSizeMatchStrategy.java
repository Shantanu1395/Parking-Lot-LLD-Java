package com.example.parkinglot.services.concreateStrategies.spotAssignment;

import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.repositories.ParkingSpotRepository;
import com.example.parkinglot.interfaces.strategy.SpotAssignmentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FlexibleSizeMatchStrategy implements SpotAssignmentStrategy {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Override
    public ParkingSpot assignSpot(Vehicle vehicle) {
        // Find a spot of the same size or larger
        Optional<ParkingSpot> spot = parkingSpotRepository.findAvailableFlexibleSpots(vehicle.getType())
                .stream()
                .findFirst();

        return spot.orElse(null);
    }
}
