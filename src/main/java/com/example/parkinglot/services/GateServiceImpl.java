package com.example.parkinglot.services;

import com.example.parkinglot.interfaces.GateService;
import com.example.parkinglot.model.Gate;
import com.example.parkinglot.model.ParkingLot;
import com.example.parkinglot.repositories.GateRepository;
import com.example.parkinglot.repositories.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateServiceImpl implements GateService {

    private final GateRepository gateRepository;
    private final ParkingLotRepository parkingLotRepository;

    // Singleton for Service Initialization
    private static GateServiceImpl instance;

    private GateServiceImpl(GateRepository gateRepository, ParkingLotRepository parkingLotRepository) {
        this.gateRepository = gateRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    // Factory Pattern for Creating Gate Objects
    @Override
    public Gate addGate(String type, String location, Long parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId)
                .orElseThrow(() -> new IllegalArgumentException("Parking lot not found"));

        Gate gate = Gate.createGate(type, location, parkingLot); // Factory Pattern
        return gateRepository.save(gate);
    }

    // Strategy Pattern for Querying Gates
    @Override
    public List<Gate> getGatesByParkingLot(Long parkingLotId) {
        parkingLotRepository.findById(parkingLotId)
                .orElseThrow(() -> new IllegalArgumentException("Parking lot not found"));

        return gateRepository.findByParkingLotId(parkingLotId);
    }

    // Simplified removal logic without Command Pattern
    @Override
    public void removeGate(Long gateId) {
        gateRepository.deleteById(gateId);
    }
}