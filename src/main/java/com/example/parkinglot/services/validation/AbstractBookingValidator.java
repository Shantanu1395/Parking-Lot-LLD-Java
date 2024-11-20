package com.example.parkinglot.services.validation;

import com.example.parkinglot.interfaces.validation.BookingValidator;
import com.example.parkinglot.model.Booking;

import javax.xml.bind.ValidationException;

public abstract class AbstractBookingValidator implements BookingValidator {
    protected BookingValidator nextValidator;

    @Override
    public void setNext(BookingValidator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(Booking request) throws ValidationException {
        if (nextValidator != null) {
            nextValidator.validate(request);
        }
    }
}
