package com.example.parkinglot.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ParkingRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spotSize;
    private double hourlyRate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "parkingSpotId", nullable = false)
    private ParkingSpot parkingSpot;

    // Getters and Setters
}
