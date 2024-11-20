package com.example.parkinglot.services;

import com.example.parkinglot.interfaces.ParkingLotService;
import com.example.parkinglot.model.ParkingLot;
import com.example.parkinglot.repositories.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    // Singleton-like behavior using Spring's @Service annotation
    private final ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    // Factory Pattern: Encapsulates creation logic for ParkingLot.
    @Override
    public ParkingLot createParkingLot(String name, String address) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);
        return parkingLotRepository.save(parkingLot);
    }

    // Facade Pattern: Simplifies interaction with the repository.
    @Override
    public List<ParkingLot> getAllParkingLots() {
        return parkingLotRepository.findAll();
    }

    @Override
    public ParkingLot getParkingLotById(Long id) {
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findById(id);
        if (parkingLotOptional.isPresent()) {
            return parkingLotOptional.get();
        } else {
            throw new IllegalArgumentException("Parking lot with ID " + id + " not found.");
        }
    }

    @Override
    public void deleteParkingLot(Long id) {
        if (parkingLotRepository.existsById(id)) {
            parkingLotRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Parking lot with ID " + id + " does not exist.");
        }
    }
}