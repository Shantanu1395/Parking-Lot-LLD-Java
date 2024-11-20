package com.example.parkinglot.repositories;

import com.example.parkinglot.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    @Query("SELECT p FROM ParkingSpot p WHERE (p.spotSize = :spotSize OR p.spotSize > :spotSize) AND p.isOccupied = false" +
            "AND (:levelId IS NULL OR p.level.id = :levelId)")
    List<ParkingSpot> findAvailableFlexibleSpots(@Param("spotSize") String spotSize, Long levelId);

    @Query("SELECT p FROM ParkingSpot p WHERE p.spotSize = :spotSize AND p.isOccupied = false " +
            "AND (:levelId IS NULL OR p.level.id = :levelId)")
    List<ParkingSpot> findAvailableSpotsBySizeAndLevel(@Param("spotSize") String spotSize, Long levelId);
}
