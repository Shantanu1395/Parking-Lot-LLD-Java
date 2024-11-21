package com.example.parkinglot.model;


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

    public static Level createLevel(int floorNumber, ParkingLot parkingLot) {
        Level level = new Level();
        level.setFloorNumber(floorNumber);
        level.setParkingLot(parkingLot);
        return level;
    }

    // Getters and Setters
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Long getId() {
        return id;
    }
}