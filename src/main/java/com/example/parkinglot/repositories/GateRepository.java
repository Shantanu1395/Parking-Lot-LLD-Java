package com.example.parkinglot.repositories;

import com.example.parkinglot.model.Gate;
import com.example.parkinglot.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GateRepository extends JpaRepository<Gate, Long> {
    List<Gate> findByParkingLotId(Long parkingLotId);
}