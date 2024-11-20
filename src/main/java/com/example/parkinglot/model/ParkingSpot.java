package com.example.parkinglot.model;

import com.example.parkinglot.enums.SpotState;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String spotSize;

    @Setter
    private boolean isOccupied;

    @Enumerated(EnumType.STRING)
    private SpotState state;


    @Setter
    @ManyToOne
    @JoinColumn(name = "levelId", nullable = false)
    private Level level;

    @OneToOne(mappedBy = "parkingSpot", cascade = CascadeType.ALL, orphanRemoval = true)
    private Vehicle vehicle;

    @OneToMany(mappedBy = "parkingSpot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingRate> parkingRates;

    public ParkingSpot(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public ParkingSpot() {
    }

    // Getters and Setters
    public void setIsOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setSpotSize(String spotSize) {
        this.spotSize = spotSize;
    }

    public void setState(SpotState state) {
        this.state = state;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public List<ParkingRate> getParkingRates() {
        return parkingRates;
    }

    public String getSpotSize() {
        return spotSize;
    }
}
