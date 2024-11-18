package com.example.parkinglot.model;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String type;
    private LocalDateTime parkedAt;

    @OneToOne
    @JoinColumn(name = "parkingSpotId", nullable = false)
    private ParkingSpot parkingSpot;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    // Getters and Setters
}