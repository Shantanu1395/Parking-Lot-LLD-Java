package com.example.parkinglot.model;

import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int floorNumber;

    @ManyToOne
    @JoinColumn(name = "parkingLotId", nullable = false)
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingSpot> parkingSpots;

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    // Getters and Setters
}