package com.example.parkinglot.interfaces;

import com.example.parkinglot.model.ParkingLot;

import java.util.List;

public interface ParkingLotService {
    ParkingLot createParkingLot(String name, String address);
    List<ParkingLot> getAllParkingLots();
    ParkingLot getParkingLotById(Long id);
    void deleteParkingLot(Long id);
}
