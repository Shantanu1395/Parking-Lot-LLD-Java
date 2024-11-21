package com.example.parkinglot.interfaces;

import com.example.parkinglot.model.Gate;
import com.example.parkinglot.model.ParkingLot;

import java.util.List;

public interface GateService {
    Gate addGate(String type, String location, ParkingLot parkingLot);
}
