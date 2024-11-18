package com.example.parkinglot.repository;

import com.example.parkinglot.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Add custom queries if needed
}
