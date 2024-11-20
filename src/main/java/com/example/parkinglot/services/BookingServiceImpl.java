package com.example.parkinglot.services;

import com.example.parkinglot.enums.BookingState;
import com.example.parkinglot.interfaces.ParkingLotService;
import com.example.parkinglot.interfaces.validation.BookingValidator;
import com.example.parkinglot.model.Booking;
import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.repositories.BookingRepository;
import com.example.parkinglot.repositories.ParkingSpotRepository;
import com.example.parkinglot.interfaces.BookingService;
import com.example.parkinglot.interfaces.strategy.FeeCalculationStrategy;
import com.example.parkinglot.services.concreateStrategies.feeCalculation.DiscountedFeeCalculationStrategy;
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

    private BookingValidator validatorChain;

    @Override
    public Booking createBooking(Long vehicleId, Long parkingSpotId, LocalDateTime startTime, LocalDateTime endTime) {

        ParkingSpot parkingSpot = parkingSpotRepository.findById(parkingSpotId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid parking spot ID"));

        if (parkingSpot.isOccupied()) {
            throw new IllegalStateException("Parking spot is already occupied.");
        }

        Booking booking = new Booking();
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setParkingSpot(parkingSpot);
        parkingSpot.setIsOccupied(true);
        booking.setState(BookingState.ACTIVE);
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
    public Booking extendBooking(Long bookingId, LocalDateTime newEndTime) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));

        booking.setEndTime(newEndTime);
        return bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));

        ParkingSpot parkingSpot = booking.getParkingSpot();
        parkingSpot.setIsOccupied(false);
        booking.setState(BookingState.CANCELLED);
        parkingSpotRepository.save(parkingSpot);
        bookingRepository.delete(booking);
    }

    @Override
    public List<Booking> getBookingsByVehicle(Long vehicleId) {
        return bookingRepository.findBookingsByVehicle_Id(vehicleId);
    }

    @Override
    public double calculateFee(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));

        long durationInHours = java.time.Duration.between(booking.getStartTime(), booking.getEndTime()).toHours();
        FeeCalculationStrategy strategy = getStrategyForSpotFees(booking.getParkingSpot());
        return strategy.calculateFee(booking.getParkingSpot(), durationInHours);
    }

    private FeeCalculationStrategy getStrategyForSpotFees(ParkingSpot parkingSpot) {
        if ("LARGE".equalsIgnoreCase(parkingSpot.getSpotSize())) {
            return new DiscountedFeeCalculationStrategy(10.0);
        } else {
            return new StandardFeeCalculationStrategy();
        }
    }
}