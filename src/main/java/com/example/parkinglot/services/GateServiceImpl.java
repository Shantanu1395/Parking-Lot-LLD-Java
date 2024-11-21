package com.example.parkinglot.services;

import com.example.parkinglot.interfaces.GateService;
import com.example.parkinglot.model.Gate;
import com.example.parkinglot.model.ParkingLot;
import com.example.parkinglot.repositories.GateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateServiceImpl implements GateService {

    private final GateRepository gateRepository;

    private GateServiceImpl(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    // Factory Pattern for Creating Gate Objects
    @Override
    public Gate addGate(String type, String location, ParkingLot parkingLot) {
        Gate gate = Gate.createGate("Entrance", "Front", parkingLot);
        return gateRepository.save(gate);
    }
}