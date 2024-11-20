package com.example.parkinglot.interfaces.validation;

import com.example.parkinglot.model.Booking;

import javax.xml.bind.ValidationException;

public interface BookingValidator {
    void setNext(BookingValidator nextValidator);
    void validate(Booking request) throws ValidationException;
}
