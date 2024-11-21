package com.example.parkinglot.model;

import com.example.parkinglot.enums.SpotState;

import javax.persistence.*;
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

    private double baseParkingRate;

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

    public void setBaseParkingRate() {
        switch (spotSize){
            case "Small":
                baseParkingRate = 10;
                break;
            case "Medium":
                baseParkingRate = 20;
            case "Large":
                baseParkingRate = 30;
                break;
            default:
                baseParkingRate = 5;
                break;
        }
    }

    public double getBaseParkingRate(){
        return baseParkingRate;
    }
}
