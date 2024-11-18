package com.example.parkinglot.model;

import javax.persistence.*;

@Entity
public class Gate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String location;

    @ManyToOne
    @JoinColumn(name = "parkingLotId", nullable = false)
    private ParkingLot parkingLot;

    // Getters and Setters
}
