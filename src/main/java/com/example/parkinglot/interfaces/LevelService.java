package com.example.parkinglot.interfaces;

import com.example.parkinglot.model.Level;

import java.util.List;

public interface LevelService {
    Level addLevel(int floorNumber, Long parkingLotId);
    List<Level> getLevelsByParkingLot(Long parkingLotId);
    void removeLevel(Long levelId);
}
