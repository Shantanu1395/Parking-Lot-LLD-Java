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

    public static Gate createGate(String type, String location, ParkingLot parkingLot) {
        Gate gate = new Gate();
        gate.setType(type);
        gate.setLocation(location);
        gate.setParkingLot(parkingLot);
        return gate;
    }

    // Getters and Setters

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
