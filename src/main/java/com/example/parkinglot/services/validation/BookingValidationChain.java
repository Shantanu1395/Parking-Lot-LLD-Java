package com.example.parkinglot.services.validation;

import com.example.parkinglot.interfaces.ParkingLotService;
import com.example.parkinglot.interfaces.validation.BookingValidator;
import com.example.parkinglot.services.validation.concreteValidations.AuthenticationValidator;
import com.example.parkinglot.services.validation.concreteValidations.BusinessHoursValidator;
import com.example.parkinglot.services.validation.concreteValidations.SpotAvailabilityValidator;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class BookingValidationChain {
    public static BookingValidator createChain(ParkingLotService parkingLotService) {
        BookingValidator authValidator = new AuthenticationValidator();
        BookingValidator businessHoursValidator = new BusinessHoursValidator(
                LocalTime.of(6, 0), LocalTime.of(22, 0));
        BookingValidator spotAvailabilityValidator = new SpotAvailabilityValidator(parkingLotService);
        BookingValidator authenticationValidator = new AuthenticationValidator();
        // Add more validators as needed

        // Chain the validators
        authValidator.setNext(businessHoursValidator);
        businessHoursValidator.setNext(spotAvailabilityValidator);

        return authValidator; // Head of the chain
    }
}
