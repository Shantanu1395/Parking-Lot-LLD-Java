package com.example.parkinglot.repositories;

import com.example.parkinglot.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM ParkingSpot p WHERE (p.spotSize = :spotSize OR p.spotSize > :spotSize) AND p.isOccupied = false")
    List<ParkingSpot> findAvailableFlexibleSpots(@Param("spotSize") String spotSize);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM ParkingSpot p WHERE p.spotSize = :spotSize AND p.isOccupied = false ")
    List<ParkingSpot> findAvailableSpotsBySize(@Param("spotSize") String spotSize);

    List<ParkingSpot> findAllByIsOccupiedFalse();

}
