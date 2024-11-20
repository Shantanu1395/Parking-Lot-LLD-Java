package com.example.parkinglot.services;

import com.example.parkinglot.model.ParkingLot;
import com.example.parkinglot.repositories.ParkingLotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParkingLotServiceImplTest {

    private ParkingLotRepository parkingLotRepository;
    private ParkingLotServiceImpl parkingLotService;

    @BeforeEach
    void setUp() {
        parkingLotRepository = mock(ParkingLotRepository.class);
        parkingLotService = new ParkingLotServiceImpl(parkingLotRepository);
    }

    @Test
    void shouldCreateParkingLot() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Central Parking");
        parkingLot.setAddress("123 Main Street");

        // Mock repository save
        when(parkingLotRepository.save(Mockito.any(ParkingLot.class))).thenReturn(parkingLot);

        ParkingLot result = parkingLotService.createParkingLot("Central Parking", "123 Main Street");

        assertNotNull(result);
        assertEquals("Central Parking", result.getName());
        assertEquals("123 Main Street", result.getAddress());

        // Verify save was called with any ParkingLot object
        verify(parkingLotRepository).save(Mockito.any(ParkingLot.class));
    }

    @Test
    void shouldGetAllParkingLots() {
        List<ParkingLot> parkingLots = Arrays.asList(
                new ParkingLot(1L, "Address 1"),
                new ParkingLot(2L, "Address 2")
        );

        when(parkingLotRepository.findAll()).thenReturn(parkingLots);

        List<ParkingLot> result = parkingLotService.getAllParkingLots();

        assertEquals(2, result.size());
        verify(parkingLotRepository).findAll();
    }

    @Test
    void shouldThrowExceptionWhenParkingLotNotFound() {
        when(parkingLotRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            parkingLotService.getParkingLotById(1L);
        });

        assertEquals("Parking lot with ID 1 not found.", exception.getMessage());
        verify(parkingLotRepository).findById(1L);
    }

    @Test
    void shouldDeleteParkingLot() {
        when(parkingLotRepository.existsById(1L)).thenReturn(true);

        parkingLotService.deleteParkingLot(1L);

        verify(parkingLotRepository).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentParkingLot() {
        when(parkingLotRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            parkingLotService.deleteParkingLot(1L);
        });

        assertEquals("Parking lot with ID 1 does not exist.", exception.getMessage());
        verify(parkingLotRepository).existsById(1L);
    }
}
