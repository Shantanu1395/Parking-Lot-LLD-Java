package com.example.parkinglot.services.concreateStrategies.spotAssignment;

import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.repositories.ParkingSpotRepository;
import com.example.parkinglot.interfaces.strategy.SpotAssignmentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
public class ExactSizeMatchStrategy implements SpotAssignmentStrategy {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Override
    public ParkingSpot assignSpot(Vehicle vehicle) {
        Optional<ParkingSpot> spot = parkingSpotRepository.findAvailableSpotsBySize(vehicle.getType())
                .stream()
                .findFirst();

        return spot.orElse(null);
    }
}