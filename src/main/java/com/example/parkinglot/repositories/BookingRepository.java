package com.example.parkinglot.repositories;

import com.example.parkinglot.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Add custom queries if needed
    List<Booking> findBookingsByVehicle_Id(Long vehicleId);
}
