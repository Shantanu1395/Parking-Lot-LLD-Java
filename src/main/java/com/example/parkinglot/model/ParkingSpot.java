package com.example.parkinglot.model;

import lombok.Setter;

import javax.persistence.*;
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

    public void setIsOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setSpotSize(String spotSize) {
        this.spotSize = spotSize;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    // Getters and Setters
}
