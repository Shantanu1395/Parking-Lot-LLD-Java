package com.example.parkinglot.interfaces;

import com.example.parkinglot.model.Gate;

import java.util.List;

public interface GateService {
    Gate addGate(String type, String location, Long parkingLotId);
    List<Gate> getGatesByParkingLot(Long parkingLotId);
    void removeGate(Long gateId);
}
