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
    @JoinColumn(name = "parkingSpotId", nullable = true)
    private ParkingSpot parkingSpot;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    // Static factory method
    public static Vehicle create(String licensePlate, String type) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(licensePlate);
        vehicle.setType(type);
        vehicle.setParkedAt(null); // Initialize parkedAt to null
        return vehicle;
    }

    // Getters and Setters

    public void setType(String type) {
        this.type = type;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setParkedAt(LocalDateTime parkedAt) {
        this.parkedAt = parkedAt;
    }

    public String getType() {
        return type;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setVehicleType(String vehicleType) {
        vehicleType = vehicleType;
    }

    public void setId(Long id) {
        this.id = id;
    }
}