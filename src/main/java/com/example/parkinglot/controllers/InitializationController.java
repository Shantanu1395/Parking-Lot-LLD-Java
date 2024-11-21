package com.example.parkinglot.controllers;

import com.example.parkinglot.enums.SpotState;
import com.example.parkinglot.interfaces.GateService;
import com.example.parkinglot.interfaces.LevelService;
import com.example.parkinglot.interfaces.ParkingLotService;
import com.example.parkinglot.interfaces.ParkingSpotService;
import com.example.parkinglot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/initialize")
public class InitializationController {

    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private GateService gateService;

    @Autowired
    private LevelService levelService;

    @Autowired
    private ParkingSpotService spotService;

    @PostMapping
    public ResponseEntity<ParkingLot> initialize(@RequestParam String name,
                                                 @RequestParam int numGates,
                                                 @RequestParam int numLevels,
                                                 @RequestParam int numSpotsPerLevel) {
        // Step 1: Create Parking Lot
        ParkingLot parkingLot = parkingLotService.createParkingLot(name);

        // Step 2: Add Gates
        for (int gate = 1; gate <= numGates; gate++) {
            gateService.addGate("Entrance", "Front", parkingLot);
        }

        // Step 3: Add Levels
        for (int level = 1; level <= numLevels; level++) {
            Level currentLevel = levelService.addLevel(level, parkingLot);

            // Step 4: Add Spots to the Level
            for (int spot = 1; spot <= numSpotsPerLevel; spot++) {
                spotService.createSpot("Small", currentLevel.getId());
            }
        }

        return ResponseEntity.ok(parkingLot);
    }
}
