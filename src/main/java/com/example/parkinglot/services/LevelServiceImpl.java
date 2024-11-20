package com.example.parkinglot.services;

import com.example.parkinglot.model.Level;
import com.example.parkinglot.model.ParkingLot;
import com.example.parkinglot.repositories.LevelRepository;
import com.example.parkinglot.repositories.ParkingLotRepository;
import com.example.parkinglot.interfaces.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    private final ParkingLotRepository parkingLotRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository, ParkingLotRepository parkingLotRepository) {
        this.levelRepository = levelRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public Level addLevel(int floorNumber, Long parkingLotId) {
        Optional<ParkingLot> parkingLot = parkingLotRepository.findById(parkingLotId);
        if (parkingLot.isEmpty()) {
            throw new IllegalArgumentException("Parking lot not found with id: " + parkingLotId);
        }

        // Factory Pattern for creating Level
        Level newLevel = Level.createLevel(floorNumber, parkingLot.get());
        return levelRepository.save(newLevel);
    }

    @Override
    public List<Level> getLevelsByParkingLot(Long parkingLotId) {
        return levelRepository.findByParkingLotId(parkingLotId);
    }

    @Override
    public void removeLevel(Long levelId) {
        if (!levelRepository.existsById(levelId)) {
            throw new IllegalArgumentException("Level not found with id: " + levelId);
        }
        levelRepository.deleteById(levelId);
    }
}
