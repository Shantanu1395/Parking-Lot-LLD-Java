package com.example.parkinglot.services.concreateStrategies.spotAssignment;

import com.example.parkinglot.interfaces.strategy.SpotAssignmentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpotAssignmentFactory {

    @Autowired
    private  ExactSizeMatchStrategy exactSizeMatchStrategy;

    @Autowired
    private  FlexibleSizeMatchStrategy flexibleSizeMatchStrategy;

    public SpotAssignmentStrategy ChooseStrategy(boolean exact){
        if (exact){
            return exactSizeMatchStrategy;
        }else{
            return flexibleSizeMatchStrategy;
        }
    }
}
