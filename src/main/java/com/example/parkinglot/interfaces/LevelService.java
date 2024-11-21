package com.example.parkinglot.interfaces;

import com.example.parkinglot.model.Level;
import com.example.parkinglot.model.ParkingLot;

import java.util.List;

public interface LevelService {
    Level addLevel(int floorNumber, ParkingLot parkingLot);
}
