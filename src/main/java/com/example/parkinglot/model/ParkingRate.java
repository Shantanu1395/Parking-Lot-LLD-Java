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

    // Private constructor to enforce the use of Builder
    private ParkingRate() {}

    // Getters (no setters, as we're using Builder for immutability)
    public Long getId() {
        return id;
    }

    public String getSpotSize() {
        return spotSize;
    }

    public double getHourlyRate() {
        return hourlyRate;
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

    // Builder class
    public static class Builder {
        private String spotSize;
        private double hourlyRate;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private ParkingSpot parkingSpot;

        public Builder spotSize(String spotSize) {
            this.spotSize = spotSize;
            return this;
        }

        public Builder hourlyRate(double hourlyRate) {
            this.hourlyRate = hourlyRate;
            return this;
        }

        public Builder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder parkingSpot(ParkingSpot parkingSpot) {
            this.parkingSpot = parkingSpot;
            return this;
        }

        public ParkingRate build() {
            ParkingRate parkingRate = new ParkingRate();
            parkingRate.spotSize = this.spotSize;
            parkingRate.hourlyRate = this.hourlyRate;
            parkingRate.startTime = this.startTime;
            parkingRate.endTime = this.endTime;
            parkingRate.parkingSpot = this.parkingSpot;
            return parkingRate;
        }
    }
}
