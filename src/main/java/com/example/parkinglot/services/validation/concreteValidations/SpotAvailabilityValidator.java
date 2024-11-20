package com.example.parkinglot.services.validation.concreteValidations;

import com.example.parkinglot.enums.BookingState;
import com.example.parkinglot.interfaces.ParkingLotService;
import com.example.parkinglot.model.Booking;
import com.example.parkinglot.services.validation.AbstractBookingValidator;

import javax.xml.bind.ValidationException;

public class SpotAvailabilityValidator extends AbstractBookingValidator {
    private ParkingLotService parkingLotService;

    public SpotAvailabilityValidator(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public void validate(Booking booking) throws ValidationException {

        if (booking.getState().equals(BookingState.COMPLETED)) {
            throw new ValidationException("Parking spot is not available.");
        }
        super.validate(booking);
    }
}
