package com.example.parkinglot.services;

import com.example.parkinglot.enums.BookingState;
import com.example.parkinglot.interfaces.ParkingLotService;
import com.example.parkinglot.interfaces.ParkingSpotService;
import com.example.parkinglot.interfaces.validation.BookingValidator;
import com.example.parkinglot.model.Booking;
import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.repositories.BookingRepository;
import com.example.parkinglot.repositories.ParkingSpotRepository;
import com.example.parkinglot.interfaces.BookingService;
import com.example.parkinglot.interfaces.strategy.FeeCalculationStrategy;
import com.example.parkinglot.services.concreateStrategies.feeCalculation.DiscountedFeeCalculationStrategy;
import com.example.parkinglot.services.concreateStrategies.feeCalculation.FeeCalculationFactory;
import com.example.parkinglot.services.concreateStrategies.feeCalculation.StandardFeeCalculationStrategy;
import com.example.parkinglot.services.validation.BookingValidationChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private ParkingSpotService parkingSpotService;

    @Autowired
    private FeeCalculationFactory feeCalculationFactory;

    private BookingValidator validatorChain;

    @Override
    public Booking createBooking(Vehicle vehicle, Long parkingSpotId, LocalDateTime startTime, LocalDateTime endTime) {

        ParkingSpot parkingSpot = parkingSpotRepository.findById(parkingSpotId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid parking spot ID"));

        Booking booking = new Booking();
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setParkingSpot(parkingSpot);
        parkingSpot.setIsOccupied(true);
        booking.setState(BookingState.ACTIVE);
        booking.setVehicle(vehicle);
        parkingSpotRepository.save(parkingSpot);

        try {
            validatorChain =  BookingValidationChain.createChain(parkingLotService);
            validatorChain.validate(booking);
        } catch (ValidationException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
        return bookingRepository.save(booking);
    }

    @Override
    public Booking endBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));

        booking.setState(BookingState.COMPLETED);
        parkingSpotService.releaseSpot(booking.getParkingSpot().getSpotId());
        return bookingRepository.save(booking);
    }

    @Override
    public double calculateFee(Booking booking) {
        return feeCalculationFactory.ChooseStrategy(booking).calculateFee(booking);
    }

    public Booking findById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with ID: " + bookingId));
    }
}