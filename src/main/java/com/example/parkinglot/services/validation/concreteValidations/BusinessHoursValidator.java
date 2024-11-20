package com.example.parkinglot.services.validation.concreteValidations;

import com.example.parkinglot.model.Booking;
import com.example.parkinglot.services.validation.AbstractBookingValidator;

import javax.xml.bind.ValidationException;
import java.time.LocalTime;

public class BusinessHoursValidator extends AbstractBookingValidator {
    private LocalTime openingTime;
    private LocalTime closingTime;

    public BusinessHoursValidator(LocalTime openingTime, LocalTime closingTime) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    @Override
    public void validate(Booking request) throws ValidationException {
        LocalTime bookingTime = request.getStartTime().toLocalTime();

        if (bookingTime.isBefore(openingTime) || bookingTime.isAfter(closingTime)) {
            throw new ValidationException("Booking time is outside of business hours.");
        }
        super.validate(request);
    }
}
