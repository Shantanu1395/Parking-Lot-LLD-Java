package com.example.parkinglot.services.validation.concreteValidations;

import com.example.parkinglot.model.Booking;
import com.example.parkinglot.services.validation.AbstractBookingValidator;

import javax.xml.bind.ValidationException;

public class AuthenticationValidator extends AbstractBookingValidator {
    @Override
    public void validate(Booking request) throws ValidationException {
        super.validate(request);
    }
}
