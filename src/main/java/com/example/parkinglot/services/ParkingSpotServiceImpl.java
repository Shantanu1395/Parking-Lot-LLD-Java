package com.example.parkinglot.services;

import com.example.parkinglot.enums.SpotState;
import com.example.parkinglot.model.Level;
import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.repositories.LevelRepository;
import com.example.parkinglot.repositories.ParkingSpotRepository;
import com.example.parkinglot.repositories.VehicleRepository;
import com.example.parkinglot.interfaces.ParkingSpotService;
import com.example.parkinglot.interfaces.strategy.SpotAssignmentStrategy;
import com.example.parkinglot.services.concreateStrategies.spotAssignment.SpotAssignmentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private SpotAssignmentFactory spotAssignmentFactory;

    @Override
    public ParkingSpot createSpot(String spotSize, Long levelId) {
        Optional<Level> levelOptional = levelRepository.findById(levelId);
        if (levelOptional.isEmpty()) {
            throw new IllegalArgumentException("Level not found for ID: " + levelId);
        }

        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSpotSize(spotSize);
        parkingSpot.setIsOccupied(false);
        parkingSpot.setLevel(levelOptional.get());

        return parkingSpotRepository.save(parkingSpot);
    }

    @Transactional
    @Override
    public ParkingSpot assignSpotToVehicle(Vehicle vehicle) {

        lock.writeLock().lock();
        try{
            ParkingSpot assignedSpot = spotAssignmentFactory.ChooseStrategy(true).assignSpot(vehicle);

            if (assignedSpot == null) {
                throw new IllegalStateException("No available parking spot for vehicle type: " + vehicle.getType());
            }

            assignedSpot.setIsOccupied(true);
            assignedSpot.setVehicle(vehicle);
            assignedSpot.setState(SpotState.OCCUPIED);
            assignedSpot.setBaseParkingRate();
            vehicle.setParkingSpot(assignedSpot);

            parkingSpotRepository.save(assignedSpot);
            vehicleRepository.save(vehicle);

            return assignedSpot;
        }finally {
            lock.writeLock().unlock();
        }
    }

    @Transactional
    @Override
    public void releaseSpot(Long parkingSpotId) {

        try{
            lock.writeLock().lock();

            Optional<ParkingSpot> parkingSpotOptional = parkingSpotRepository.findById(parkingSpotId);

            if (parkingSpotOptional.isEmpty()) {
                throw new IllegalArgumentException("Parking spot not found for ID: " + parkingSpotId);
            }

            ParkingSpot parkingSpot = parkingSpotOptional.get();
            if (parkingSpot.getState().equals(SpotState.AVAILABLE)) {
                throw new IllegalStateException("Parking spot is already vacant.");
            }

            Vehicle vehicle = parkingSpot.getVehicle();
            parkingSpot.setIsOccupied(false);
            parkingSpot.setVehicle(null);
            parkingSpot.setState(SpotState.AVAILABLE);

            if (vehicle != null) {
                vehicle.setParkingSpot(null);
                vehicleRepository.save(vehicle);
            }

            parkingSpotRepository.save(parkingSpot);
        }finally {
            lock.writeLock().unlock();
        }
    }
}
