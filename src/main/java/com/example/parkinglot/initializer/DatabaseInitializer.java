package com.example.parkinglot.initializer;

import com.example.parkinglot.model.Level;
import com.example.parkinglot.model.ParkingLot;
import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.repository.LevelRepository;
import com.example.parkinglot.repository.ParkingLotRepository;
import com.example.parkinglot.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class DatabaseInitializer {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @PostConstruct
    public void init() {
        // Create a ParkingLot
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Central Parking");
        parkingLot.setAddress("123 Main Street");
        parkingLotRepository.save(parkingLot);

        // Create Levels
        Level level1 = new Level();
        level1.setFloorNumber(1);
        level1.setParkingLot(parkingLot);

        Level level2 = new Level();
        level2.setFloorNumber(2);
        level2.setParkingLot(parkingLot);

        levelRepository.saveAll(Arrays.asList(level1, level2));

        // Create Parking Spots
        ParkingSpot spot1 = new ParkingSpot();
        spot1.setSpotSize("Small");
        spot1.setIsOccupied(false);
        spot1.setLevel(level1);

        ParkingSpot spot2 = new ParkingSpot();
        spot2.setSpotSize("Medium");
        spot2.setIsOccupied(false);
        spot2.setLevel(level1);

        ParkingSpot spot3 = new ParkingSpot();
        spot3.setSpotSize("Large");
        spot3.setIsOccupied(false);
        spot3.setLevel(level2);

        parkingSpotRepository.saveAll(Arrays.asList(spot1, spot2, spot3));
    }
}
