package com.example.parkinglot.services.concreateStrategies.feeCalculation;

import com.example.parkinglot.interfaces.strategy.FeeCalculationStrategy;
import com.example.parkinglot.model.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeeCalculationFactory {

    @Autowired
    private StandardFeeCalculationStrategy standardFeeCalculationStrategy;

    @Autowired
    private DiscountedFeeCalculationStrategy discountedFeeCalculationStrategy;

    public FeeCalculationStrategy ChooseStrategy(Booking booking){
        if (booking.getParkingSpot().getVehicle().getType().equals("Small")){
            return standardFeeCalculationStrategy;
        }else{
            return discountedFeeCalculationStrategy;
        }
    }
}
