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

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Level addLevel(int floorNumber, ParkingLot parkingLot) {
        Level level = Level.createLevel(floorNumber, parkingLot);
        return levelRepository.save(level);
    }
}
