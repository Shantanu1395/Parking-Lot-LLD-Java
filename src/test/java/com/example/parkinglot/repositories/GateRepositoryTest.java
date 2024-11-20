package com.example.parkinglot.repositories;

import com.example.parkinglot.model.Gate;
import com.example.parkinglot.model.ParkingLot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GateRepositoryTest {

    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Test
    void shouldFindGatesByParkingLotId() {
        // Setup: Create a ParkingLot and Gates
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Test Parking Lot");
        parkingLot.setAddress("123 Test Street");
        parkingLot = parkingLotRepository.save(parkingLot);

        Gate gate1 = new Gate();
        gate1.setType("Entry");
        gate1.setLocation("North");
        gate1.setParkingLot(parkingLot);

        Gate gate2 = new Gate();
        gate2.setType("Exit");
        gate2.setLocation("South");
        gate2.setParkingLot(parkingLot);

        gateRepository.saveAll(List.of(gate1, gate2));

        // Act
        List<Gate> gates = gateRepository.findByParkingLotId(parkingLot.getId());

        // Assert
        assertNotNull(gates);
        assertEquals(2, gates.size());
    }
}
