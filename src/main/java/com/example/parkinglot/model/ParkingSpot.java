package com.example.parkinglot.model;

import com.example.parkinglot.enums.SpotState;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spotSize;

    private boolean isOccupied;

    @Enumerated(EnumType.STRING)
    private SpotState state;

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
    public synchronized void setIsOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setSpotSize(String spotSize) {
        this.spotSize = spotSize;
    }

    public synchronized void setState(SpotState state) {
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

    public Long getSpotId(){
        return id;
    }

    public Level getLevel() {
        return level;
    }

    public SpotState getState() {
        return state;
    }
}
