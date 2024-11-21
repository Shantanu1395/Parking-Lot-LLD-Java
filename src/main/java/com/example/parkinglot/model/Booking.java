package com.example.parkinglot.model;

import com.example.parkinglot.enums.BookingState;
import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BookingState state;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "parkingSpotId", nullable = false)
    private ParkingSpot parkingSpot;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;

    public Booking() {
    }

    public Booking(LocalDateTime startTime, LocalDateTime endTime, ParkingSpot parkingSpot, Vehicle vehicle) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
    }

    // Getters and Setters

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setState(BookingState state) {
        this.state = state;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public BookingState getState() {
        return state;
    }

    public Long getId() {
        return id;
    }

    public long calculateDurationInHours() {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Start time and end time cannot be null");
        }

        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }

        // Calculate the duration between startTime and endTime
        Duration duration = Duration.between(startTime, endTime);

        // Convert the duration to minutes
        return duration.toHours();
    }
}
